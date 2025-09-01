import java.util.*;
import org.json.*; // You can use org.json library to parse JSON

public class ans {
    public static void main(String[] args) {
        // Example JSON string (your first testcase)
        String jsonString = "{\n" +
                "    \"keys\": {\n" +
                "        \"n\": 4,\n" +
                "        \"k\": 3\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"4\"\n" +
                "    },\n" +
                "    \"2\": {\n" +
                "        \"base\": \"2\",\n" +
                "        \"value\": \"111\"\n" +
                "    },\n" +
                "    \"3\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"12\"\n" +
                "    },\n" +
                "    \"6\": {\n" +
                "        \"base\": \"4\",\n" +
                "        \"value\": \"213\"\n" +
                "    }\n" +
                "}";

        // Parse JSON
        JSONObject obj = new JSONObject(jsonString);
        JSONObject keys = obj.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        // Step 1: Extract first k roots
        List<Integer> roots = new ArrayList<>();
        for (int i = 1; i <= n && roots.size() < k; i++) {
            if (obj.has(String.valueOf(i))) {
                JSONObject rootObj = obj.getJSONObject(String.valueOf(i));
                int base = Integer.parseInt(rootObj.getString("base"));
                String value = rootObj.getString("value");
                int root = Integer.parseInt(value, base); // convert to decimal
                roots.add(root);
            }
        }

        System.out.println("Roots (decimal) = " + roots);

        // Step 2: Build polynomial (x - r1)(x - r2)...(x - rk)
        // Represent polynomial as coefficients list
        // Start with P(x) = 1
        List<Integer> coeffs = new ArrayList<>();
        coeffs.add(1); // coefficient for x^0

        for (int root : roots) {
            // Multiply current polynomial by (x - root)
            List<Integer> newCoeffs = new ArrayList<>(Collections.nCopies(coeffs.size() + 1, 0));

            for (int i = 0; i < coeffs.size(); i++) {
                // term for x
                newCoeffs.set(i + 1, newCoeffs.get(i + 1) + coeffs.get(i));
                // term for -root
                newCoeffs.set(i, newCoeffs.get(i) - root * coeffs.get(i));
            }
            coeffs = newCoeffs;
        }

        // Step 3: Print polynomial coefficients
        // Highest degree first
        System.out.print("Polynomial coefficients: ");
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            System.out.print(coeffs.get(i) + (i > 0 ? ", " : ""));
        }
    }
}
