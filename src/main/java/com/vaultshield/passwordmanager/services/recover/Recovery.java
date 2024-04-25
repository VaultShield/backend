package com.vaultshield.passwordmanager.services.recover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Recovery {

	private static final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    private static final String[] WORDS = {
        "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "kiwi", "lemon",
	    "mango", "nectarine", "orange", "papaya", "quince", "raspberry", "strawberry", "tangerine", "ugli", "vine",
	    "watermelon", "xigua", "yam", "zucchini", "apricot", "blueberry", "cantaloupe", "dragonfruit", "eggplant", "feijoa",
	    "guava", "huckleberry", "ilama", "jackfruit", "kumquat", "lime", "mulberry", "nashi", "olive", "peach",
	    "quenepa", "rosehip", "soursop", "tomato", "ube", "voavanga", "walnut", "ximenia", "yucca", "ziziphus",
	    "acerola", "bilberry", "currant", "dewberry", "elder", "fingerlime", "gooseberry", "hackberry", "jaboticaba", "kei apple",
	    "lychee", "marionberry", "navel", "oat", "persimmon", "quinoa", "rowan", "sapote", "thimbleberry", "ulluco",
	    "velvet apple", "wineberry", "xenia", "yellow", "zapote", "almond", "beet", "carrot", "daikon", "endive",
	    "fennel", "ginger", "horseradish", "iceberg", "jicama", "kale", "leek", "mustard", "nopales", "okra",
	    "parsley", "quail", "rutabaga", "spinach", "turnip", "cool", "vanilla", "wasabi", "xoconostle", "yautia",
	    "zedoary", "margarita", "vault", "shield",
    };
	
	public static List<String> generateSeedPhrase(int length){
		List<String> words = new ArrayList<>(List.of(WORDS));
		Collections.shuffle(words);
		return words.subList(0, length);
	}

	public static String hashSeedPhrase(List<String> seedPhrase){
		String combinedPhrase = String.join(";", seedPhrase);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(combinedPhrase);
	}

	public static Boolean compareHash(List<String> seedPhrase, String savedSeedPhrase){
		String combinedPhrase = String.join(";", seedPhrase);
			if (bcrypt.matches(combinedPhrase, savedSeedPhrase)){
				return true;
			}

		return false;
	}
}
