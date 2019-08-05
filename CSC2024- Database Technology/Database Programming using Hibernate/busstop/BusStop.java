/**
 * csc2024-hibernate-assignment
 *
 * Copyright (c) 2015 Newcastle University
 * Email: <h.firth@ncl.ac.uk/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package uk.ac.ncl.cs.csc2024.busstop;

import uk.ac.ncl.cs.csc2024.route.Route;

import javax.persistence.*;
import java.util.Set;

/**
 * Hibernate BusStop Entity
 *
 * Task: Create fields, methods and annotations which implicitly define an
 * appropriate database table schema for BusStop records.
 *
 * @author hugofirth
 */
@Entity
@NamedQueries({ @NamedQuery(name = BusStop.SELECT_ALL, query = "select b from BusStop b order by b.id asc"),
		@NamedQuery(name = BusStop.SELECT_MAX_ID, query = "select b from BusStop b where max(b.ID)") })
@Table(name = "bus_stop")
public class BusStop {
	@Id
	@Column(name = "STOP_ID")
	private int ID;
	@Column(name = "DESCRIPTION")
	private String Description;

	public int getID() {
		return ID;
	}

	public String getDescription() {
		return Description;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@OneToMany(mappedBy = "Start", fetch = FetchType.LAZY)

	private Set<Route> Start;
	@OneToMany(mappedBy = "End", fetch = FetchType.LAZY)

	private Set<Route> End;

	public static final String SELECT_ALL = "BusStop.selectAll";
	public static final String SELECT_MAX_ID = "BusStop.selectMaxId";

}
