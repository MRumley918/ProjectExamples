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
package uk.ac.ncl.cs.csc2024.route;

import uk.ac.ncl.cs.csc2024.busstop.BusStop;
import uk.ac.ncl.cs.csc2024.operator.Operator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@NamedQueries({ @NamedQuery(name = Route.SELECT_ALL, query = "select r from Route r order by r.number asc"),
		@NamedQuery(name = Route.SELECT_ALL_FOR_RAILWAY_STATION, query = "select r from Route r, BusStop b where b.description= Railway Station and b.Start or b.End = r.routeNumber"),
		@NamedQuery(name = Route.CUMULATIVE_FREQ_BY_OK_TRAVEL, query = "select sum(frequency) from Route r, Operator o where o.name= OK Travel and o.routes= r.operators") })
@Table(name = "route")
public class Route {
	public String getRouteNumber() {
		return RouteNumber;
	}

	public int getFrequency() {
		return Frequency;
	}

	public BusStop getStart() {
		return Start;
	}

	public BusStop getEnd() {
		return End;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	@Id
	@Column(name = "ROUTE_NUMBER")
	private String RouteNumber;
	@Column(name = "FREQUENCY")
	private int Frequency;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "START")

	private BusStop Start;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "END")

	private BusStop End;

	@ManyToMany(cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "routes", targetEntity = Operator.class, fetch = FetchType.LAZY)
	private Set<Operator> operators;

	public void setRouteNumber(String routeNumber) {
		RouteNumber = routeNumber;
	}

	public void setFrequency(int frequency) {
		Frequency = frequency;
	}

	public void setStart(BusStop start) {
		Start = start;
	}

	public void setEnd(BusStop end) {
		End = end;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public static final String SELECT_ALL = "Route.selectAll";
	public static final String SELECT_ALL_FOR_RAILWAY_STATION = "Route.selectAllForRailwayStation";
	public static final String CUMULATIVE_FREQ_BY_OK_TRAVEL = "Route.cumulativeFreqByOKTravel";
}
