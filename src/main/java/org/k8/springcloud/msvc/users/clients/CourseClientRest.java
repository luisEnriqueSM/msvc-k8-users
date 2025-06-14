package org.k8.springcloud.msvc.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-k8-courses")
public interface CourseClientRest {

    @DeleteMapping("/eliminar-curso-usuario/{userId}")
    void deleteCourseUser(@PathVariable Long userId);

}
