package com.dmnoky.taxidermy.validator.user;

import com.dmnoky.taxidermy.model.user.Worker;
import com.dmnoky.taxidermy.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class WorkerValidator extends UserValidator<Worker> {

    public void setWorkerService(UserService<Worker> workerService) {
        super.userService = workerService;
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        taskClear((Worker) target);
    }

    @Override
    public Worker validateUpdate(Object target, Errors errors) {
        taskClear((Worker) target);
        return super.validateUpdate(target, errors);
    }

    private void taskClear(Worker worker) {
        if (worker.getTasks() != null) {
            for (int i = 0; i < worker.getTasks().size(); i++ ) {
                if (worker.getTasks().get(i).isEmpty()) {
                    worker.getTasks().remove(i);
                    i--;
                }
            }
        }
    }
}
