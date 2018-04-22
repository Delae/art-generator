import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Title {

	private static enum TitleElementType {
		GENITIVE, VERB, CONTEXT
	}

	public String get() {
		String title = generateTitle();
		return title;
	}

	private int getTitleElementsNumber() {
		int titleElementsNumber = (int) (Math.random() * 4);
		return titleElementsNumber;
	}

	/*
	 * 
	 * --- RULES FOR TITLE'S CREATION ---
	 * 
	 * The title is formed by one noun + between 0 and 3 elements The genitive
	 * can only be placed after the noun or another genitive 2 genitives max 1
	 * verb max 1 context max
	 * 
	 */

	private String[] getTitleElementsTypes(int titleElementsNumber) {
		boolean isGenitiveForbidden = false;
		List<String> tmp = new ArrayList<String>();
		if (titleElementsNumber > 0) {
			for (int i = 0; i < titleElementsNumber; i++) {

				// If title contains 3 elements, first one must be a genitive
				if (titleElementsNumber > 2 && i == 0) {
					tmp.add(TitleElementType.GENITIVE.toString());

				} else {

					// Disable genitives if other title element is added
					if (i > 0 && !tmp.get(i - 1).equals(TitleElementType.GENITIVE.toString())) {
						isGenitiveForbidden = true;
					}

					tmp.add(getTitleElementType(isGenitiveForbidden));

					// Duplicate genitives management
					if (i > 0) {
						while (tmp.get(0).equals(tmp.get(1))) {
							tmp.remove(1);
							tmp.add(getTitleElementType(isGenitiveForbidden));
						}
					}

					if (i == 2) {
						// Avoid triple genitives
						if (tmp.get(1).equals(TitleElementType.GENITIVE.toString())) {
							tmp.remove(2);
							isGenitiveForbidden = true;
							tmp.add(getTitleElementType(isGenitiveForbidden));
							// Avoid double contexts
						} else if (tmp.get(1).equals(TitleElementType.CONTEXT.toString())) {
							tmp.remove(2);
							tmp.add(TitleElementType.VERB.toString());
							// Avoid double verbs
						} else if (tmp.get(1).equals(TitleElementType.VERB.toString())) {
							tmp.remove(2);
							tmp.add(TitleElementType.CONTEXT.toString());
						}
					}
				}

			}
		}
		String[] types = (String[]) tmp.toArray(new String[0]);
		return types;
	}

	private String getTitleElementType(boolean isGenitiveForbidden) {
		int titleElementsNumber = 0;
		if (isGenitiveForbidden) {
			titleElementsNumber = (int) (Math.random() * 2);
		} else {
			titleElementsNumber = (int) (Math.random() * 3);
		}
		String elementType = null;
		switch (titleElementsNumber) {
		case 0:
			elementType = TitleElementType.CONTEXT.toString();
			break;
		case 1:
			elementType = TitleElementType.VERB.toString();
			break;
		case 2:
			elementType = TitleElementType.GENITIVE.toString();
			break;
		}
		return elementType;
	}

	private String generateTitle() {
		String title = getTitleElementFromFile("title_parts/noun.txt");
		String[] titleElements = getTitleElementsTypes(getTitleElementsNumber());
		if (titleElements.length > 0) {
			for (int i = 0; i < titleElements.length; i++) {
				String separator = " ";
				if(i >= 1){
					separator = "\n";
				}
				if (titleElements[i].equals(TitleElementType.GENITIVE.toString())) {
					title = title + separator + getTitleElementFromFile("title_parts/genitive.txt");
				} else if (titleElements[i].equals(TitleElementType.VERB.toString())) {
					title = title + separator + getTitleElementFromFile("title_parts/verb.txt");
				} else if (titleElements[i].equals(TitleElementType.CONTEXT.toString())) {
					title = title + separator + getTitleElementFromFile("title_parts/context.txt");
				}
			}
		}
		title = title.substring(0, 1).toUpperCase() + title.substring(1);
		return title;
	}

	private String getTitleElementFromFile(String filePath) {
		InputStream input = getClass().getResourceAsStream(filePath);
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		try {
			in = new BufferedReader(new InputStreamReader(input, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			System.out.println("Couldn't read file in UTF-8.");
		}
		List<String> linesList = new ArrayList<String>();
		try {
			String line = null;
			while ((line = in.readLine()) != null) {
				linesList.add(line);
			}
		} catch (IOException e) {
			System.out.println("File couldn't be read properly.");
		}
		String[] titleElementsList = linesList.toArray(new String[0]);
		int titleElementIndex = (int) (Math.random() * titleElementsList.length);
		String titleElement = titleElementsList[titleElementIndex];
		return titleElement;
	}
}