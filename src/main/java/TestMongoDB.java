import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class TestMongoDB {
	public static void main(String[] args) {

		//String user = "DESKTOP-31T8EG1";
		String user = "student01";
		String password = "student01";
		String host = "localhost";
		int port = 27017;
		String database = "database01";
		String clientURI = "mongodb://" + user + ":" + password + "@" + host + ":" + port + "/" + database;
		MongoClientURI uri = new MongoClientURI(clientURI);

		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase(database);
		db.getCollection("people").drop();
		
		MongoCollection<Document> mcollection = db.getCollection("people");

		Operacja operacja = new Operacja();
		Scanner in = new Scanner(System.in);
		String wybor = "";

		while (wybor != "0") {
			System.out.println("----------------------------------------------- \n" +
					"Każda cyfra odpowiada następującej operacji: \n" +
					"0 - wychodzi z programu \n" +
					"1 - zapisz (dodaje dane)\n" +
					"2 - aktualizuj (zmienia litery w nazwie ) \n" +
					"3 - kasuj (usuwa wszystkie dane)\n" +
					"4 - pobierz (wyswietla po nazwie) \n" +
					"5 - pobierz (wyswietla filmy 'To' lub 'SpiderMan' tylko te które trwają od 60 do 90 minut)\n" +
					"6 - pobierz (wyswietla wszystko)\n" +
					"7 - przetwórz (dodatkowe 10 minut do wybranego filmu)\n" +
					"----------------------------------------------- \n"
			);

			wybor = in.nextLine();

			switch (wybor) {
				case "1":
					mcollection = operacja.zapiszDoBazy(mcollection);
					break;
				case "2":
					mcollection = operacja.zmienLitery(mcollection);
					break;
				case "3":
					mcollection = operacja.usunWszystko(mcollection);
					break;
				case "4":
					operacja.pobierzPoNazwie(mcollection);
					break;
				case "5":
					operacja.pobierzPoDacie(mcollection);
					break;
				case "6":
					operacja.pobierzWszystko(mcollection);
					break;
				case "7":
					operacja.przetworz(mcollection);
					break;
				default:
					System.out.println("\u001B[31mbledny znak \u001B[37m");
			}
		}
		mongoClient.close();
	}
}