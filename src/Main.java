import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter validWriter = null;
        BufferedWriter errorWriter = null;

        try {
            reader = new BufferedReader(new FileReader("user_data.txt"));
            validWriter = new BufferedWriter(new FileWriter("valid_data.txt"));
            errorWriter = new BufferedWriter(new FileWriter("error_data.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] userData = line.split(",");
                    if (userData.length != 3) {
                        throw new Exception("Missing Data");
                    }

                    String name = userData[0].trim();
                    String email = userData[1].trim();
                    int age = Integer.parseInt(userData[2].trim());

                    if (age <= 0) {
                        throw new Exception("Invalid Age");
                    }


                    validWriter.write(line + "\n");
                } catch (Exception e) {
                    
                    errorWriter.write(line + " - " + e.getMessage() + "\n");
                }
            }

            System.out.println("Data validation completed.");
        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (validWriter != null) {
                    validWriter.close();
                }
                if (errorWriter != null) {
                    errorWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing files: " + e.getMessage());
            }
        }
    }
}
