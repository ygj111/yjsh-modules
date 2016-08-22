/* Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hhh.workflow.controller;

import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.util.FundPage;
import com.hhh.workflow.service.EngineService;

/**
 * 委托授权
 * @since 0.1
 */
@RestController
@RequestMapping(value = "/user/surrogate")
public class SurrogateController {
	@Autowired
	private EngineService facets;
	
	@RequestMapping(value = "list/{pageno}/pagesize/{pagesize}", method = RequestMethod.GET)
	public FundPage<Surrogate> list(Integer pageno, Integer pagesize) {
		Page<Surrogate> page = facets.searchSurrogate(new PageRequest(pageno, pagesize), new QueryFilter());
		return new FundPage<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String create(Surrogate entity) {
		entity.setSdate(entity.getSdate()+" 00:00:00");
		entity.setEdate(entity.getEdate()+" 23:59:59");
		facets.addSurrogate(entity);
		return "true";
	}

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public Surrogate view(@PathVariable("id") String id) {
		return facets.getSurrogate(id);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id) {
		facets.deleteSurrogate(id);
		return "true";
	}
}
