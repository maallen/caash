package com.rpm.caash;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.rpm.caash.mongodb.MongoDBApiOperator;
import com.rpm.model.Job;

@Path("/")
public class CaashServer {
	
	private MongoDBApiOperator mongoDBOperator = MongoDBApiOperator.getInstance();
	
	@GET
	@Path("/testHello")
	public String helloWorld(){
		return "Get in!!! It worked!!!";
	}
	
	@GET
	@Path("/getJobs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DBObject> getJobs(){
		DB db = mongoDBOperator.getMongoDb();
		DBCollection collection = db.getCollection("Jobs");
		DBCursor jobs = collection.find();
		return jobs.toArray();
	}

	@POST
	@Path("/createJob")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createJobs(Job job){
		DBObject dbJob = new BasicDBObject("description", job.getDescription())
				.append("location", job.getLocation())
				.append("jobName", job.getJobName())
				.append("jobPrice", job.getJobPrice());	
		DB db = mongoDBOperator.getMongoDb();
		DBCollection collection = db.getCollection("Jobs");
		collection.insert(dbJob);
	}


}
