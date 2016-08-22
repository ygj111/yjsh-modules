package com.hhh.fund.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hhh.fund.web.model.DataTablesResult;
import com.hhh.fund.web.model.FileMeta;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.zhuozhengsoft.pageoffice.FileSaver;



@Controller
@RequestMapping("/fileupload")
public class FileuploadController {
	@Autowired 
	private Mongo mongo;
    @Autowired 
    private MongoTemplate mongoTemplate;
	private final static String PAGE_SIZE="10";
	@RequestMapping(value="/main")
	public String adminMain(){
		return "/fileManage/fileupload3";
	} 
	
	@RequestMapping(value="/searchFile")
	public String sarchFile(){
		return "/fileManage/searchFile";
	} 
	
	//基于uploadify
    @RequestMapping("/uploadTest")
    public @ResponseBody String uploadAttachment_sheryzp(MultipartHttpServletRequest request,HttpServletResponse response) throws IOException {
    	 Map map = new HashMap();
         request.setCharacterEncoding("utf-8");
         DiskFileItemFactory factory = new DiskFileItemFactory();
         String path = request.getRealPath("/file");
         factory.setRepository(new File(path));
         factory.setSizeThreshold(1024*1024) ;
         ServletFileUpload upload = new ServletFileUpload(factory);
         try {
             //可以上传多个文件
             List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
             for(FileItem item : list){
                 if(!item.isFormField()){
                     String name = item.getName() ;
                     String fileSuffix  = name.substring(name.lastIndexOf(".")+1,name.length());
                     String oldName = name.replaceAll("." + fileSuffix,"");
                     InputStream in = item.getInputStream() ;
                     int length = 0 ;
                     byte [] buf = new byte[1024] ;
                     while( (length = in.read(buf) ) != -1){
                      //   out.write(buf, 0, length);
                     }
                     in.close();
                    // out.close();
                     /**将上传处理后的数据返回**/
                     map.put("fileSuffix",fileSuffix);
                     map.put("fileName",oldName);
                     //map.put("filePath",fileName);
                     break;
                 }
             }
         }catch (Exception e) {
             System.out.println("出错了：" + e.getMessage());
         }
         response.setContentType("text/xml; charset=UTF-8");
         response.setHeader("Cache-Control", "no-cache");
         response.setHeader("Pragma", "no-cache");
         PrintWriter out = response.getWriter();
       //  JSONObject jsonObject = JSONObject.fromObject(map);
         String msg =  "";
         out.print(msg);
         out.close();
         return "0";
    }
    
    /**
     * fileupload上传文件
     * */
    LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //1.获取所有文件的迭代器
         Iterator<String> itr =  request.getFileNames();
         response.setContentType("text/plain");
         MultipartFile mpf = null;
         if(files.size()>0){
        	 files.clear();
         }
         long start = new Date().getTime();  
         //2.获得其中一个文件
         while(itr.hasNext()){
             mpf = request.getFile(itr.next()); 
             //如果文件超过10个，进行清理
             if(files.size() >= 10){
                 files.pop();
             }
             String name = mpf.getOriginalFilename();
             if(name == null){
            	 FileMeta fileMeta1 = new FileMeta();
        		 fileMeta1.setFileName("文件上传有误!");
        		 files.add(fileMeta1);
        		 return files;
             }
             String name1 = name.substring(name.length()-4, name.length());
            /* if(!name1.equals(".xls")&&!name1.equals(".doc")&&!name1.equals("docx")&&!name1.equals("xlsx")){
            		 FileMeta fileMeta2 = new FileMeta();
            		 fileMeta2.setFileName("请上传正确的文件格式!");
            		 files.add(fileMeta2);
            		 return files;
             }*/
             //创建上传后，返回的实体
             FileMeta fileMeta = new FileMeta();
             try{   
            	 //连结mongodb数据库
            	// DB mydb = createConn();
            	/* MongoClient mongoClient = new MongoClient( "localhost", 27017 );
            	 MongoDatabase mongoDatabase = mongoClient.getDatabase("testGridFS");  
         	    System.out.println("Connect to database successfully");
         	    DB mydb = mongoClient.getDB("testGridFS");*/
            	 UserBean user = ShiroUtils.getUser();
  		         String username = user.getUsername();
            	 DB mydb = mongoTemplate.getDb();
            	 //获取文件列表
  		         GridFS myFS = new GridFS(mydb);
  		         //创建文件
  		         GridFSInputFile inputFile = myFS.createFile(mpf.getBytes());
  		         //判断文件是否存在mongo中
  		         DBObject query=new BasicDBObject("filename",mpf.getOriginalFilename());  
  		         query.put("userName", username);
		          //查询的结果：  
		          List<GridFSDBFile> listfiles=myFS.find(query);  
		          if(listfiles.size()>0){
		        	  FileMeta fileMeta2 = new FileMeta();
	            		 fileMeta2.setFileName("文件已存在，请修改文件名!");
	            		 files.add(fileMeta2);
	            		 return files;
		          }
  		         inputFile.setFilename(mpf.getOriginalFilename());
  		         inputFile.put("userName", username);
  		         inputFile.save();  
  		         String fileId = inputFile.getId().toString();
  		         fileMeta.setFileId(fileId);
  		         fileMeta.setFileName(mpf.getOriginalFilename());
  		         fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
  		         if(name1.indexOf(".") !=-1){
  		        	 name1 = name1.substring(1);
  		         }
  		         fileMeta.setFileType(name1);
  		         //mongoUtil.closeMongo();
  		      // mongo.close();
  		      }catch(Exception e){
  		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  		     }
             files.add(fileMeta);
         }
        return files;
    }
    
    /**
     * 
     * 保存 在pageOffice中修改的文档
     * */
    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, HttpServletResponse response){
    	FileSaver fs=new FileSaver(request,response);
    	InputStream in = fs.getFileStream();
    	String fileId = request.getParameter("fileId");
    	OutputStream out;
    	try{   
    			//DB mydb = mongoUtil.createConn();
    		/*MongoClient mongoClient = new MongoClient( "localhost", 27017 );
       	 MongoDatabase mongoDatabase = mongoClient.getDatabase("testGridFS");  
    	    System.out.println("Connect to database successfully");
    	    DB mydb = mongoClient.getDB("testGridFS");*/
    		 DB mydb = mongoTemplate.getDb();
		         GridFS myFS = new GridFS(mydb);
		         //查找条件  
		          DBObject query=new BasicDBObject("_id",new ObjectId(fileId));  
		          //查询的结果：  
		          List<GridFSDBFile> listfiles=myFS.find(query);  
		          GridFSDBFile gridDBFile = new GridFSDBFile();
		          for(int i=0;i<listfiles.size();i++){
		        	  gridDBFile=listfiles.get(i);  
		          }
		          String fileName = gridDBFile.getFilename();
		         GridFSInputFile inputFile = myFS.createFile(in);
		         inputFile.setId(new ObjectId(fileId) );
		         inputFile.setFilename(fileName);
		         UserBean user = ShiroUtils.getUser();
  		         String username = user.getUsername();
		         inputFile.put("userName", username);
		         myFS.remove(new ObjectId(fileId));
		         inputFile.save();  
		        // mongo.close();
		         in.close();
		      }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
    	fs.close();
 }
    	
    /**
     * 查找本用户上传的所有文件
     * */
    @RequestMapping("/searchAll")
    public @ResponseBody DataTablesResult<FileMeta> searchAll(String userName,int draw,@RequestParam(value = "start",defaultValue="0") int start,@RequestParam(value = "length",defaultValue=PAGE_SIZE) int pageSize){  
        try {  
        	 UserBean user = ShiroUtils.getUser();
		     String username = user.getUsername();
        	 //DB mydb = db.createConn();
		     /*MongoClient mongoClient = new MongoClient( "localhost", 27017 );
        	 MongoDatabase mongoDatabase = mongoClient.getDatabase("testGridFS");  
     	    System.out.println("Connect to database successfully");
     	    DB mydb = mongoClient.getDB("testGridFS");*/
		     DB mydb = mongoTemplate.getDb();
     	  /*  mongo.*/
		         GridFS myFS = new GridFS(mydb);
		         //查找条件  
		          DBObject query=new BasicDBObject("userName",username); 
		          
		        //查询的结果：  
		          List<GridFSDBFile> listfiles = myFS.find(query);
		          //List<GridFSDBFile> listfiles=gridfsTemplate.find(new Query(Criteria.where("userName").is(username)));  
		          GridFSDBFile gridDBFile = new GridFSDBFile();
		          LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		          for(int i=0;i<listfiles.size();i++){
		        	  //拿到一个gridFs
		        	  gridDBFile=listfiles.get(i);  
		        	  FileMeta fileMeta = new FileMeta();
		        	  fileMeta.setFileName(gridDBFile.getFilename());
		        	  String fileSize = gridDBFile.get("length").toString();
		        	  Date date = gridDBFile.getUploadDate();
		        	  SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制   
		        	  String uploadDate = sdformat.format(date);
		        	  fileMeta.setUpdateDate(uploadDate);
		        	  long filesize = Long.parseLong(fileSize);
		        	  fileMeta.setFileSize((filesize/1024)+"kb");
		        	  String fileName = gridDBFile.getFilename();
		        	  String name = fileName.substring(fileName.length()-4, fileName.length());
		        	  if(name.indexOf(".")!= -1){
		        		  name = name.substring(1);
		        	  }
		        	  fileMeta.setFileType(name);
		        	  fileMeta.setUserName((String)gridDBFile.get("userName"));
		        	  String id = gridDBFile.getId().toString();
		        	  fileMeta.setFileId(id);
		        	  files.add(fileMeta);
		          }
		         // mongo.close();
		          DataTablesResult<FileMeta> dtr = new DataTablesResult<FileMeta>();
		  		dtr.setData(files);
		  		dtr.setDraw(draw);
		  		dtr.setRecordsFiltered(files.size());
		  		dtr.setRecordsTotal(listfiles.size());
		         return dtr;
		      }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        return null;
		     }
    }
    
    /**
     * 跳转到pageoffice
     * */
    @RequestMapping("/look")
    public String look(String fileId,Model model,HttpServletResponse response){
    	DB mydb = mongoTemplate.getDb();
        GridFS myFS = new GridFS(mydb);
        //查找条件  
         DBObject query=new BasicDBObject("_id",new ObjectId(fileId));  
         //查询的结果：  
         List<GridFSDBFile> listfiles=myFS.find(query);  
         GridFSDBFile gridDBFile = new GridFSDBFile();
         for(int i=0;i<listfiles.size();i++){
       	  gridDBFile=listfiles.get(i);  
         }
       //获得其中的文件名  
       //注意 ： 不是fs中的表的列名，而是根据调试gridDBFile中的属性而来  
       String name=(String)gridDBFile.get("filename");  
       String name1 = name.substring(name.length()-4, name.length());
       String type = "";
       if(name1.equals(".xls")||name1.equals("xlsx")){
    	   type = "xls";
       }else if(name1.equals(".doc")||name1.equals("docx")){
    	   type = "doc";
       }
       model.addAttribute("name", name1);
       model.addAttribute("fileId", fileId);
       model.addAttribute("type", type);
       if(!type.equals("xls")&& !type.equals("doc")){
    	   return "/fileManage/test";
       }else{
    	   return "/fileManage/pgOfficeWord";
       }
    }
    
    /**
     * 打开pageOffice 在线浏览
     * */
    @RequestMapping("/download")
    public void downloadFile(String fileId,HttpServletResponse response){
    	//String fileId = (String)request.getAttribute("fileId");
        response.setCharacterEncoding("utf-8");  
        response.setContentType("multipart/form-data");  
        response.setHeader("Content-Disposition", "attachment;fileId="+fileId);  
        try {  
        	//DB mydb = mongoUtil.createConn();
        	/*MongoClient mongoClient = new MongoClient( "localhost", 27017 );
       	 MongoDatabase mongoDatabase = mongoClient.getDatabase("testGridFS");  
    	    System.out.println("Connect to database successfully");
    	    DB mydb = mongoClient.getDB("testGridFS");*/
        	 DB mydb = mongoTemplate.getDb();
		         GridFS myFS = new GridFS(mydb);
		         //查找条件  
		          DBObject query=new BasicDBObject("_id",new ObjectId(fileId));  
		          //查询的结果：  
		          List<GridFSDBFile> listfiles=myFS.find(query);  
		          GridFSDBFile gridDBFile = new GridFSDBFile();
		          for(int i=0;i<listfiles.size();i++){
		        	  gridDBFile=listfiles.get(i);  
		          }
		        //获得其中的文件名  
		        //注意 ： 不是fs中的表的列名，而是根据调试gridDBFile中的属性而来  
		        String fileName1=(String)gridDBFile.get("filename");  
		        OutputStream os=response.getOutputStream();  
		        gridDBFile.writeTo(os);
		      //    mongo.close();
		       // mongoUtil.closeMongo();
		      }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
    }
    /**
     * 删除文件
     * */
    @RequestMapping("/deleteFile")
    public @ResponseBody String deleteFile(String id){
    	 try {  
         	//DB mydb = mongoUtil.createConn();
         	/*MongoClient mongoClient = new MongoClient( "localhost", 27017 );
        	 MongoDatabase mongoDatabase = mongoClient.getDatabase("testGridFS");  
     	    System.out.println("Connect to database successfully");
     	    DB mydb = mongoClient.getDB("testGridFS");*/
    		 DB mydb = mongoTemplate.getDb();
 		         GridFS myFS = new GridFS(mydb);
 		         //查找条件  
 		        DBObject query = new BasicDBObject();
 		        id = id.replaceAll("undefined", "");
 		         String[] ids = id.split(";");
 		         for(int i=0;i<ids.length;i++){
 		        	 String id1 = ids[i];
 		        	 query=new BasicDBObject("_id",new ObjectId(id1));
 		        	 myFS.remove(new ObjectId(id1));
 		         }
 		         // mongo.close();
 		          return "succ";
 		       // mongoUtil.closeMongo();
 		      }catch(Exception e){
 		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 		        return "error";
 		     }
    }
}
