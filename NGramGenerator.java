package ie.atu.sw;

public class NGramGenerator {

	// ### NGramGenerator Comments ###//
	// Creates  ngrams at size specified by user
	// Ngrams found that are smaller than the specified size are still added
	public String[] generateNGrams(String s, int n) {
		if (s.length() >= n) {

			String[] ngrams = new String[(s.length() - n) + 1];
			for (int i = 0; i <= (s.length() - n); i++) {
				ngrams[i] = s.substring(i, i + n);
			}
			return ngrams;
		} else {
			String[] temp = new String[1];
			temp[0] = s;
			return temp;
		}
	}
}
