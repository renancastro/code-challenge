package pt.visualnuts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JsonCountriesParser {

    private JSONArray countries;

    public static void main(String[] args) {
        JsonCountriesParser parser = new JsonCountriesParser();
        System.out.println(String.format("Total number of countries is %s", parser.getNumberOfCountries()));
        System.out.println(String.format("The country with most official languages is %s", parser.getCountryWithMostOfficialLanguagesConsideringGerman()));
        System.out.println(String.format("The number of official languages spoken in listed countries is %s", parser.countTotalLanguagesSpokenInAllCountries()));
        System.out.println(String.format("The country with the highest number of official languages is %s", parser.getCountryWithHighestNumberOfOfficialLanguages()));
        System.out.println(String.format("The most common official languages are %s", parser.getTheMostCommonLanguage()));
    }

    /**
     * Returns the number of countries in the world
     *
     * @return
     */
    private long getNumberOfCountries() {
        return getCountries().stream().count();
    }

    /**
     * Finds the country with the most official languages, where they officially speak German (de).
     *
     * @return
     */
    private String getCountryWithMostOfficialLanguagesConsideringGerman() {
        JSONObject result = (JSONObject) getCountries().stream()
                .filter(country -> ((JSONArray) ((JSONObject) country).get("languages")).contains("de"))
                .max(Comparator.comparingInt(item -> ((JSONArray) ((JSONObject) item).get("languages")).size()))
                .orElse(new JSONObject());
        Object country = result.get("country");
        return country != null ? country.toString() : "none";
    }

    /**
     * Counts all the official languages spoken in the listed countries.
     *
     * @return
     */
    private int countTotalLanguagesSpokenInAllCountries() {
        List<String> distinctLanguages = (List<String>) getCountries().stream()
                .flatMap(country -> ((JSONArray) ((JSONObject) country).get("languages")).stream())
                .distinct()
                .collect(Collectors.toList());
        return distinctLanguages.size();
    }

    /**
     * Find the country with the highest number of official languages.
     *
     * @return
     */
    private String getCountryWithHighestNumberOfOfficialLanguages() {
        JSONObject result = (JSONObject) getCountries().stream()
                .max(Comparator.comparingInt(item -> ((JSONArray) ((JSONObject) item).get("languages")).size()))
                .orElse(new JSONObject());
        Object country = result.get("country");
        return country != null ? country.toString() : "none";
    }

    /**
     * Find the most common official language(s), of all countries
     * @return
     */
    private String getTheMostCommonLanguage() {
        List<String> allLanguages = (List<String>) getCountries().stream()
                .flatMap(country -> ((JSONArray) ((JSONObject) country).get("languages")).stream())
                .collect(Collectors.toList());

        // Counter the number of times each language appears on the stream, used a HashMap, so I don't need to worry
        // about duplications
        Map<String, Integer> languagesCounter = new HashMap<>();
        allLanguages.forEach(language ->
                languagesCounter.put(language, Collections.frequency(allLanguages, language))
        );

        // Sort by descendant order
        List<Map.Entry<String, Integer>> languageList = new ArrayList<>(languagesCounter.entrySet());
        languageList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Assuming that I had no previous description of how many languages I should take (maybe a rank of top 3)
        // I took the first one with most usages and considered it as a threshold of value
        int topOneLanguage = languageList.get(0).getValue();
        List<Map.Entry<String, Integer>> collect = languageList.stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() == topOneLanguage).collect(Collectors.toList());

        return collect.toString();
    }

    private JSONArray getCountries() {
        if (countries == null) {
            countries = getJSONArrayFromFile();
        }
        return countries;
    }

    private static JSONArray getJSONArrayFromFile() {
        String fileName = "src\\main\\resources\\countries.json";
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(new FileReader(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}