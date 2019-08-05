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
package uk.ac.ncl.cs.csc2024.operator;

import uk.ac.ncl.cs.csc2024.route.Route;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Hibernate Operator Entity
 *
 * Task: Create fields, methods and annotations which implicitly define an
 * appropriate database table schema for Operator records.
 *
 * @author hugofirth
 */
@Entity
@NamedQueries({ @NamedQuery(name = Operator.SELECT_ALL, query = "select o from Operator o order by o.name asc"),
		@NamedQuery(name = Operator.SELECT_ALL_BY_DIAMOND_BUSES, query = "select r from Route r, Operator o where o.name=Diamond Buses and o.routes=r.operators"),
		@NamedQuery(name = Operator.SELECT_ALL_FOR_PARK_GATES, query = "select o from Operator o, Route r, BusStop b where b.description=Park Gates and (b.Start or b.End) = (r.Start or r.End) and r.operators=o.routes") })
@Table(name = "operator")
public class Operator {
	public String getName() {
		return Name;
	}

	public String getStreet() {
		return Street;
	}

	public String getTown() {
		return Town;
	}

	public String getPostcode() {
		return Postcode;
	}

	public String getEmail() {
		return Email;
	}

	public String getPhone() {
		return Phone;
	}

	@Id
	@Column(name = "NAME")
	private String Name;
	@Column(name = "STREET")
	private String Street;
	@Column(name = "TOWN")
	private String Town;
	@Column(name = "POSTCODE")
	private String Postcode;
	@Column(name = "EMAIL")
	private String Email;
	@Column(name = "PHONE")
	private String Phone;

	public void setName(String name) {
		Name = name;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public void setTown(String town) {
		Town = town;
	}

	public void setPostcode(String postcode) {
		Postcode = postcode;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	@ManyToMany(targetEntity = Route.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY

	)
	@JoinTable(name = "Operates", joinColumns = @JoinColumn(name = "OPERATOR_ID"), inverseJoinColumns = @JoinColumn(name = "ROUTE_ID"))

	private Set<Route> routes;

	public static final String SELECT_ALL = "Operator.selectAll";
	public static final String SELECT_ALL_BY_DIAMOND_BUSES = "Operator.selectAllByDiamondBuses";
	public static final String SELECT_ALL_FOR_PARK_GATES = "Operator.selectAllForParkGates";

}
