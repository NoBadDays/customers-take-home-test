package nobaddays.customers;

import nobaddays.customers.pojo.Customer;
import nobaddays.customers.utils.CustomerUtils;
import nobaddays.customers.utils.FileReader;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class CustomersApplication {

	private static final Logger LOGGER = Logger.getLogger(CustomersApplication.class.getName());

	public static void main(String[] args) throws IOException, JSONException {
		SpringApplication.run(CustomersApplication.class, args);

        LOGGER.log(Level.INFO, () -> "Getting List of customers within " +
                Constants.RANGE_TO_CHECK_IN_KM + "km Range of (" + Constants.OFFICE_GPS_COORDINATE + ")");

		List<Customer> customers = FileReader.getCustomers(new URL(Constants.JSON_TXT_FILE_INPUT_URL).openStream());

		List<Customer> customersInRange = CustomerUtils.getCustomersWithinDistance(
				customers, Constants.OFFICE_GPS_COORDINATE, Constants.RANGE_TO_CHECK_IN_KM);

        LOGGER.log(Level.INFO, () -> "Total customers read from file " + customers.size() + ", of which " +
                customersInRange.size() + " are within a " + Constants.RANGE_TO_CHECK_IN_KM + " Range.");


        //LOGGER.log(Level.INFO, () -> CustomerUtils.printListOfCustomersAsJson(customersInRange));

        LOGGER.log(Level.INFO, () -> "List of customers in range: " + System.lineSeparator() +
                        customersInRange // TODO Sort by UserID
                );

	}



}
