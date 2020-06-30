import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.Random;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.inc;


public class Operacja {

    final private static Random r = new Random(System.currentTimeMillis());

    MongoCollection<Document> zapiszDoBazy(MongoCollection<Document> collection) {

        Document doc1;

        Long key1 = (long) Math.abs(r.nextInt());
        byte key11 = (byte) Math.abs(r.nextInt(60));
        StworzFilm film = new StworzFilm();


        doc1 = film.stworz(key1, "To", key11 + 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key1 + " => " + doc1.toJson());
        doc1.clear();

        Long key2 = (long) Math.abs(r.nextInt());
        byte key12 = (byte) Math.abs(r.nextInt(60));
        doc1 = film.stworz(key2, "Avengers", key12+ 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key2 + " => " + doc1.toJson());
        doc1.clear();

        Long key3 = (long) Math.abs(r.nextInt());
        byte key13 = (byte) Math.abs(r.nextInt(60));
        doc1 = film.stworz(key3, "Toy Story 4", key13+ 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key3 + " => " + doc1.toJson());
        doc1.clear();

        Long key4 = (long) Math.abs(r.nextInt());
        byte key14 = (byte) Math.abs(r.nextInt(60));
        doc1 = film.stworz(key4, "Terminator", key14+ 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key4 + " => " + doc1.toJson());
        doc1.clear();

        Long key5 = (long) Math.abs(r.nextInt());
        byte key15 = (byte) Math.abs(r.nextInt(60));
        doc1 = film.stworz(key5, "Bleach", key15+ 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key5 + " => " + doc1.toJson());
        doc1.clear();

        Long key6 = (long) Math.abs(r.nextInt());
        byte key16 = (byte) Math.abs(r.nextInt(60));
        doc1 = film.stworz(key6, "SpiderMan", key16+ 30);
        collection.insertOne(doc1);
        System.out.println("PUT " + key6 + " => " + doc1.toJson());
        doc1.clear();

        return collection;
    }

    void pobierzWszystko(MongoCollection<Document> collection) {
        System.out.println("Wszystkie filmy: ");
        for (Document doc : collection.find())
            System.out.println(doc.toJson());
    }

    public static void pobierzPoNazwie(MongoCollection<Document> collection) {

        Scanner in = new Scanner(System.in);
        System.out.println("Podaj nazwę filmu którego chcesz wyszukać");
        String wybor = "";
        wybor = in.nextLine();


        for (Document doc : collection.find(or(eq(Film.NAZWA.toString(), wybor)))) {
            System.out.println(doc.toJson());
        }
    }

    public static void pobierzPoDacie(MongoCollection<Document> collection) {

        for (Document d : collection.find(and(gte("DLUGOSCSEANSU", 60), lte("DLUGOSCSEANSU", 90), or(eq("NAZWA", "To"), eq("NAZWA", "SpiderMan")))))
            System.out.println(d.toJson());

    }


    MongoCollection<Document> przetworz(MongoCollection<Document> collection) {

        Scanner in = new Scanner(System.in);
        System.out.println("Podaj nazwę filmu którego chcesz przedłużyć");
        String nazwa = "";
        nazwa = in.nextLine();

        UpdateResult updateResult = collection.updateMany(eq("NAZWA", nazwa), inc("DLUGOSCSEANSU", 10));

        System.out.println("Liczba filmów o przedłużonym czasie seansu: " + updateResult.getModifiedCount());


        return collection;
    }

    MongoCollection<Document> zmienLitery(MongoCollection<Document> collection) {
        for (Document d : collection.find())
        {
            if(d.get("NAZWA").toString().equals(d.get("NAZWA").toString().toUpperCase())){
                collection.updateOne(eq("_id", d.get("_id")), new Document("$set", new Document("NAZWA", d.get("NAZWA").toString().toLowerCase()  )));
            } else{
                collection.updateOne(eq("_id", d.get("_id")), new Document("$set", new Document("NAZWA", d.get("NAZWA").toString().toUpperCase()  )));
            }
        }
        return collection;
    }

    MongoCollection<Document> usunWszystko(MongoCollection<Document> collection) {

        DeleteResult deleteResult = collection.deleteMany(exists("NAZWA"));
        System.out.println("Liczba usuniętych filmów:" + deleteResult.getDeletedCount());

        return collection;
    }


}
