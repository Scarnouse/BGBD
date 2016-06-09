package com.boreas.modelo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBJDBC {
	
	//private static DBCollection coleccion = null;
	
	private static MongoClient cliente = null;
	
	private MongoDBJDBC(){}
	
	/*public static DBCollection getMongo(){
		
		if(coleccion==null)	{
			MongoClient cliente = new MongoClient();
			DB db = (DB) cliente.getDatabase("test");
			coleccion = db.createCollection("Juegos", null);
		}
		return coleccion;
	}*/
	
	public static MongoClient getMongo(){
		if (cliente==null){
			cliente = new MongoClient();
		}
		return cliente;
	}
}
