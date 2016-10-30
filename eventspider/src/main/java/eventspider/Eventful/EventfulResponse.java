package eventspider.Eventful;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class EventfulResponse{
	@JsonProperty("page_number")
	private String page_number;
	@JsonProperty("last_item")
	private Object last_item;
	@JsonProperty("first_item")
	private Object first_item;
	@JsonProperty("page_items")
	private Object page_items;
	@JsonProperty("total_items")
	private String total_items;
	@JsonProperty("page_count")
	private String page_count;
	@JsonProperty("events")
	private EventsList events;
	@JsonProperty("page_size")
	private String page_size;
	@JsonProperty("search_time")
	private String search_time;

	public void setPage_number(String page_number){
		this.page_number = page_number;
	}

	public String getPage_number(){
		return page_number;
	}

	public void setLast_item(Object last_item){
		this.last_item = last_item;
	}

	public Object getLast_item(){
		return last_item;
	}

	public void setFirst_item(Object first_item){
		this.first_item = first_item;
	}

	public Object getFirst_item(){
		return first_item;
	}

	public void setPage_items(Object page_items){
		this.page_items = page_items;
	}

	public Object getPage_items(){
		return page_items;
	}

	public void setTotal_items(String total_items){
		this.total_items = total_items;
	}

	public String getTotal_items(){
		return total_items;
	}

	public void setPage_count(String page_count){
		this.page_count = page_count;
	}

	public String getPage_count(){
		return page_count;
	}

	public void setEvents(EventsList events){
		this.events = events;
	}

	public EventsList getEvents(){
		return events;
	}

	public void setPage_size(String page_size){
		this.page_size = page_size;
	}

	public String getPage_size(){
		return page_size;
	}

	public void setSearch_time(String search_time){
		this.search_time = search_time;
	}

	public String getSearch_time(){
		return search_time;
	}

}