yjsh fund 框架初使化说明
==============
*修改数据连接：yjsh-web工程下 /yjsh-web/src/main/resources/dev/db.properties
*要生成数据库表修改：修改yjsh-web工程下 com.hhh.fund.config.AppContext类中jpaVendorAdapter.setGenerateDdl(false);将false改成 true;
*如果是新生成的数据库表，访问 /initdata 初始化admin用户数据，初始密码为：123456

