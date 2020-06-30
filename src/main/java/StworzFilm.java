import org.bson.Document;

public class StworzFilm {

    Document stworz(Long id, String nazwa, int dlugoscseansu) {
        return new Document("_id", id)
                .append(Film.NAZWA.toString(), nazwa)
                .append(Film.DLUGOSCSEANSU.toString(), dlugoscseansu);
    }
}
