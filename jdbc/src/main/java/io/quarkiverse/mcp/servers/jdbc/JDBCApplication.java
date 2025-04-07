package io.quarkiverse.mcp.servers.jdbc;

import io.quarkiverse.mcp.servers.shared.SharedApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * This is the main entry point for the jdbc server.
 * It will detect the jdbc url and (re)start the server with the proper driver.
 */
@QuarkusMain(name = "jdbc")
public class JDBCApplication {
    public static void main(String[] args) {
        //this is a giant hack :)
        //if first argument is "nobootstrap" we skip the bootstrap otherwise
        //we use it as a marker to know that this is the user invoking the server
        if (args.length > 1 && args[0].equals("nobootstrap")) {
            SharedApplication.main(args, (remainingArgs) -> {
                if (remainingArgs.size() > 0) {
                    System.setProperty("fileserver.paths", String.join(",", remainingArgs));
                }
                return null;
            });
        } else {
            Bootstrap.main(args);
        }
    }
}
