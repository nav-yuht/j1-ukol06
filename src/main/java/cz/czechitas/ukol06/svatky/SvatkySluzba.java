package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;
    // načíst seznam svátků ze souboru svatky.json

    public SvatkySluzba() throws IOException {
        // TODO načíst seznam svátků ze souboru svatky.json

        // Následující řádek po vlastní implementaci smažte.
         seznamSvatku = nacistSeznamSvatku();
    }
    private SeznamSvatku nacistSeznamSvatku() throws IOException {
        // Read and parse the svatky.json file into SeznamSvatku objects
        return objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
    }
    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        // TODO
        // získat seznam svátků
        List<Svatek> svatky= seznamSvatku.getSvatky();
        // převést na Stream
        List<String> odpovidajiciJmeno = svatky.stream()
        // pomocí metody filter() vybrat jen ty, které odpovídají zadanému dni (porovnat MonthDay pomocí metodyequals())
                .filter(svat -> svat.getJmeno().equals(day))//why is it not working with 'svatky'
        // pomocí metody map() získat z objektu jméno
                .map(Svatek::getJmeno)
        // pomocí toList() převést na List
                 .collect(Collectors.toList());
        // Následující řádek po vlastní implementaci smažete.
        return odpovidajiciJmeno;
    }

    // jiny pokus


   /* public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        // Get the list of holidays
        List<Svatek> svatky = seznamSvatku.getSvatky();

        // Filter holidays matching the given day, map to names, and collect to List
        List<String> odpovidajiciJmeno = svatky.stream()
                .filter(svatky -> svatky.getDen().equals(day))
                .map(Svatek::getJmeno)
                .collect(Collectors.toList());

        return odpovidajiciJmeno;
    }*/
}
