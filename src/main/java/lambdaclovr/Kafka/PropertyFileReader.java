import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    private static Properties prop = new Properties();

    public static Properties readPropertyFile(String file) {
        if (prop.isEmpty()) {
            InputStream input = PropertyFileReader.class.getClassLoader().getResourceAsStream(file);

            try {
                prop.load(input);

                input.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return prop;
    }
}
