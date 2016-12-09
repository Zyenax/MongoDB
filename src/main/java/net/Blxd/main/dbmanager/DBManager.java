package net.Blxd.main.dbmanager;

import java.util.UUID;

import net.Blxd.main.Main;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;

public class DBManager implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;
	public DBManager(Main listener) {
		DBManager.plugin = listener;	
	}
	
	public static MongoClient mongoClient;
	
	public static void connect(String ip, Integer port, String dataBase, String collection){
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
            public void run() {
            	try{
            		mongoClient = new MongoClient(ip, port);
            	}catch(MongoException e){
            		System.out.print(e);
            	}
            }
        });
	}
	
	
	public static void insert(String database, String collection, String userName, UUID userID, String gamemode, Double health){
		MongoCollection<Document> coll = mongoClient.getDatabase(database).getCollection(collection);
		
		Document doc = new Document(userID.toString(), new Document()
		.append("GAMEMODE", gamemode)
		.append("USERNAME", userName)
		.append("HEALTH", health));
		
		coll.insertOne(doc);
		
		/*DBObject obj = new BasicDBObject(new Document(userID.toString(), new Document()
       	.append("NAME", userName)
        .append("GAMEMODE", gamemode)
        .append("HEALTH", health)));
		
		coll.insert(obj);*/

	}
	
	
	
	
	
	/*
	public static void insert(String database, String collection, UUID userID, String nameToInput, Object data, String nameToInput1, Object data1){
		DBCollection coll = getCollection(database, collection);
		DBObject obj = new BasicDBObject("UUID", userID.toString());
		DBObject isThere = coll.findOne(obj);
		
		if(isThere == null){
			obj.put(nameToInput, data);
			if(nameToInput1 != null || data1 != null){
				obj.put(nameToInput1, data1);
			}
			coll.insert(obj);
			return;
		}else{
			System.out.print("USER ALREADY IN DATABASE");
			return;
		}

	}
	
	public static void update(String database, String collection, UUID userID, String nameToInput, Object data, String nameToInput1, Object data1){
		DBCollection coll = getCollection(database, collection);
		DBObject obj = new BasicDBObject("UUID", userID.toString());
		DBObject isThere = coll.findOne(obj);
		
		if(isThere != null){
			obj.put(nameToInput, data);
			if(nameToInput1 != null || data1 != null){
				obj.put(nameToInput1, data1);
			}
			coll.update(isThere, obj);
			return;
		}else{
			System.out.print("USER CANT BE FOUND... ADDING");
			insert(database, collection, userID, nameToInput, data, nameToInput1, data1);
			return;
		}

	}
	
	public static String find(String database, String collection, UUID userID, String nameToInput){
		DBCollection coll = getCollection(database, collection);
		DBObject obj = new BasicDBObject("UUID", userID.toString());
		DBObject isThere = coll.findOne(obj);
		
		if(isThere != null){
			return isThere.get(nameToInput).toString();
		}else{
			System.out.print("USER DOESNT HAVE THAT VALUE");
			return null;
		}

	}*/
	
	@SuppressWarnings("deprecation")
	public static DB getDB(String database){
		return mongoClient.getDB(database);
	}
	
	public static DBCollection getCollection(String database, String collection){
		return getDB(database).getCollection(collection);
	}
	
	public static void closeConnection(){
		try{
			mongoClient.close();
		}catch(MongoException e){
			System.out.print(e);
		}
	}	

}