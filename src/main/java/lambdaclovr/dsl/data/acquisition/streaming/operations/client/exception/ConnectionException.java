package lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.exception</h3>
 * <h3>Class Name: ConnectionException</h3>
 * <p>
 * 
 * @Project This file is part of LAMBDA-CLOVR Project.
 *          </p>
 * @author Numan Khan
 * 
 * @version 1.0
 * @since 2024-27-11
 * 
 * */

@SuppressWarnings("serial")
public class ConnectionException extends RuntimeException {
    private final String causedByClass;
    private final String connectionString;

    @SuppressWarnings("rawtypes")
	public ConnectionException(final String connectionString,
                               final String message,
                               final Throwable cause,
                               final Class causedByClass) {
        super(message, cause);
        this.causedByClass = causedByClass.getName();
        this.connectionString = connectionString;

    }

    public String getCausedByClass() {
        return causedByClass;
    }

    public String getConnectionString() {
        return connectionString;
    }

}
