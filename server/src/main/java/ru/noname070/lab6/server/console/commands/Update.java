package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "update" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class Update extends AbstractCommand {

    public Update() {
        super("update", L18n.getGeneralBundle().getString("command.update.description"), false, true);
    }

    @Override
    public void execute(Organization inputOrg) {
        log.info("executed command '" + getName() + "', org:" + inputOrg.toString());
        if (!CollectionManager.getData().isEmpty()) {
            // CollectionManager.getData().set(org.getId(), org);
            CollectionManager.getData().stream()
                    .forEach(org -> {
                        if (org.getId() == inputOrg.getId()) {
                            int idx = CollectionManager.getData().indexOf(org);
                            CollectionManager.getData().set(idx, inputOrg);
                            log.info("element " + org.toString() + " with id " + idx + " replaced by " + inputOrg.toString());
                            return;
                        }
                    });
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.update.execute"),
                    inputOrg.getId());
        } else {
            log.error("Empty data");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
