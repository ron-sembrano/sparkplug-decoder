package com.ronsembrano.sparkplugdecoder.gateway;

import java.util.Optional;
import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.gateway.dataroutes.RouteGroup;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class which is instantiated by the Ignition platform when the module is loaded in the gateway scope.
 */
public class SparkplugBBirthDecoderGatewayHook extends AbstractGatewayModuleHook {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Called to before startup. This is the chance for the module to add its extension points and update persistent
     * records and schemas. None of the managers will be started up at this point, but the extension point managers will
     * accept extension point types.
     */
    @Override
    public void setup(GatewayContext context) {
        logger.info("setup ran");
    }

    /**
     * Called to initialize the module. Will only be called once. Persistence interface is available, but only in
     * read-only mode.
     */
    @Override
    public void startup(LicenseState activationState) {
        logger.info("startup ran");
    }

    /**
     * Called to shutdown this module. Note that this instance will never be started back up - a new one will be created
     * if a restart is desired
     */
    @Override
    public void shutdown() {

    }

    /**
     * @return the path to a folder in one of the module's gateway jar files that should be mounted at
     * /res/module-id/foldername
     */
    @Override
    public Optional<String> getMountedResourceFolder() {
        return Optional.empty();
    }

    /**
     * Provides a chance for the module to mount any route handlers it wants. These will be active at
     * <tt>/main/data/module-id/*</tt> See {@link RouteGroup} for details. Will be called after startup().
     */
    @Override
    public void mountRouteHandlers(RouteGroup routes) {

    }

    /**
     * Used by the mounting underneath /res/module-id/* and /main/data/module-id/* as an alternate mounting path instead
     * of your module id, if present.
     */
    @Override
    public Optional<String> getMountPathAlias() {
        return Optional.empty();
    }

    /**
     * @return {@code true} if this is a "free" module, i.e. it does not participate in the licensing system. This is
     * equivalent to the now defunct FreeModule attribute that could be specified in module.xml.
     */
    @Override
    public boolean isFreeModule() {
        return false;
    }

}
