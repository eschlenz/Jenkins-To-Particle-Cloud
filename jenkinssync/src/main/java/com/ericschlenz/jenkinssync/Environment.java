package com.ericschlenz.jenkinssync;

import java.io.StringWriter;
import javax.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

@Singleton
public class Environment {
    private static final Logger L = LogManager.getLogger(Environment.class);

    @Option(name="-j", aliases="--jenkins", usage="URL for Jenkins server. Example: http://myjenkins.com.", required=true)
    private String jenkinsUrl;
    @Option(name="-u", aliases="--username", usage="Jenkins username.")
    private String jenkinsUsername;
    @Option(name="-p", aliases="--password", usage="Jenkins password, or API access token.")
    private String jenkinsPassword;
    @Option(name="-n", aliases="--name", usage="Particle topic name to publish events to.", required=true)
    private String particleName;
    @Option(name="-l", aliases="--ttl", usage="Particle event TTL for published events.", required=true)
    private String particleTtl;
    @Option(name="-t", aliases="--token", usage="Particle API access token.", required=true)
    private String particleToken;

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public String getJenkinsUsername() {
        return jenkinsUsername;
    }

    public String getJenkinsPassword() {
        return jenkinsPassword;
    }

    public String getParticleName() {
        return particleName;
    }

    public String getParticleTtl() {
        return particleTtl;
    }

    public String getParticleToken() {
        return particleToken;
    }

    public void initialize(String[] args) {
        L.info("Initializing...");

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            L.error("Missing or incorrect parameters.");

            StringWriter sw = new StringWriter();
            sw.append("\n");

            parser.printUsage(sw, null);
            L.error(sw.toString());

            System.exit(1);
        }
    }
}
