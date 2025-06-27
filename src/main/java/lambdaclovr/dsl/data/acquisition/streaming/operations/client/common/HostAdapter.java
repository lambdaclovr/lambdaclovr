package lambdaclovr.dsl.data.acquisition.streaming.operations.client.common;

import org.apache.zookeeper.client.ConnectStringParser;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


/**
 * <h1>LAMBDA-CLOVR Project</h1>
 * <h2>Layer: Data Storage Layer: Streaming Acquisition</h2>
 * <h3>Package Name: lambdaclovr.dsl.data.acquisition.streaming.operations.client.common</h3>
 * <h3>Class Name: HostAdapter</h3>
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

public final class HostAdapter {
    private HostAdapter() {

    }

    /**
     * Parses a comma-separated string of enpoints  and returns a list of  addresses
     *
     * @param connectionString a coma-separated list of servers
     * @return a list of server addresses
     * @throws IllegalArgumentException cannot parse hosts
     */
    
    public static  List<InetSocketAddress> toList(final String connectionString) {

        final List<InetSocketAddress> hostAddresses = new ArrayList<>();
        try {
            final ConnectStringParser parser =
                    new ConnectStringParser(connectionString);

            parser.getServerAddresses().forEach(serverAddress -> {
                hostAddresses.add(new InetSocketAddress(serverAddress.getHostName(), serverAddress.getPort()));
            });
            return hostAddresses;

        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse hosts",e);
        }
    }}
