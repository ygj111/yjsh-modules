/* Copyright 2013-2015 www.snakerflow.com.
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
package org.snaker.engine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 抄送实例实体
 * @author yuqs
 * @since 1.5
 */
@Entity
@Table(name="wf_cc_order")
public class CCOrder implements Serializable {
	private static final long serialVersionUID = -7596174225209412843L;
	@EmbeddedId
	private CCOrderPK id;
	@Column(name="creator", length=50)
    private String creator;
	@Column(name="create_Time", length=50)
	private String createTime;
	@Column(name="finish_Time", length=50)
    private String finishTime;
	@Column(name="status", length=1)
	private Integer status;


    public CCOrderPK getId() {
		return id;
	}

	public void setId(CCOrderPK id) {
		this.id = id;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
