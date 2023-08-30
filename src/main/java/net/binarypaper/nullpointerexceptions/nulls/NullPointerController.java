package net.binarypaper.nullpointerexceptions.nulls;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "null-pointers")
@Slf4j
public class NullPointerController {

    /*
     * This code has no error handling at all and returns an HTTP 500 Internal Server Error
     * if a NullPointerException is thrown. 
     */
    @PostMapping(path = "1-no-null-checks")
    @ResponseStatus(HttpStatus.CREATED)
    public String getInnerValueNoNullChecks(@RequestBody Request request) {
        log.info(request.toString());
        return request
                .getOne()
                .getTwo()
                .getThree()
                .getFour()
                .getFive()
                .getSix()
                .getSeven()
                .getEight()
                .getNine()
                .getTen()
                .getValue();
    }

    /*
     * This code manually checks for null values with if statements.
     * In case of null values, the if statements will prevent NullPointerExceptions.
     * I would say this code is quite understandable, even to a non programmer, as long
     * as they have an idea of what Null is.
     * But this code is very verbose and can take some time to debug, so it is not good code.
     */
    @PostMapping(path = "2-null-checks-with-if-statements")
    @ResponseStatus(HttpStatus.CREATED)
    public String getInnerValueNullChecksWithIfStatements(@RequestBody Request request) {
        log.info(request.toString());
        // Comment: null is when a variable has no value assigned at all.
        //          This is similar to a cell in an Excel spreadsheet that is blank.
        if (request.getOne() != null) {
            One one = request.getOne();
            if (one.getTwo() != null) {
                Two two = one.getTwo();
                if (two.getThree() != null) {
                    Three three = two.getThree();
                    if (three.getFour() != null) {
                        Four four = three.getFour();
                        if (four.getFive() != null) {
                            Five five = four.getFive();
                            if (five.getSix() != null) {
                                Six six = five.getSix();
                                if (six.getSeven() != null) {
                                    Seven seven = six.getSeven();
                                    if (seven.getEight() != null) {
                                        Eight eight = seven.getEight();
                                        if (eight.getNine() != null) {
                                            Nine nine = eight.getNine();
                                            if (nine.getTen() != null) {
                                                Ten ten = nine.getTen();
                                                if (ten.getValue() != null) {
                                                    return ten.getValue();
                                                } else {
                                                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inner value not specified");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }   
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inner value not specified");
    }

    /*
     * This code prevents NullPointerExceptions by using Java Optional class and stream functions.
     * The code is very consice and is easy to understand by someone who understands Java Optional and
     * stream functions.
     *  A non programmer or person not familiar with the above concepts will not have any idea what this code is doing.
     *  It looks like it is something to do with maps and not clear this is about null values.
     */
    @PostMapping(path = "3-null-checks-with-optional")
    @ResponseStatus(HttpStatus.CREATED)
    public String getInnerValueNullChecksWithOptional(@RequestBody Request request) {
        log.info(request.toString());
        Optional<String> innerValue = Optional.ofNullable(request)
                .map(Request::getOne)
                .map(One::getTwo)
                .map(Two::getThree)
                .map(Three::getFour)
                .map(Four::getFive)
                .map(Five::getSix)
                .map(Six::getSeven)
                .map(Seven::getEight)
                .map(Eight::getNine)
                .map(Nine::getTen)
                .map(Ten::getValue);
        if (innerValue.isPresent()) {
            return innerValue.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inner value not specified");
        }
    }

    /*
     * I think this code is just as consice as the Java optional solution,
     * but it makes it more clear that you are dealing with nuls that could occur.
     */
    @PostMapping(path = "4-null-checks-with-try-catch")
    @ResponseStatus(HttpStatus.CREATED)
    public String getInnerValueNullChecksWithTryCatch(@RequestBody Request request) {
        log.info(request.toString());
        try {
            return request
                    .getOne()
                    .getTwo()
                    .getThree()
                    .getFour()
                    .getFive()
                    .getSix()
                    .getSeven()
                    .getEight()
                    .getNine()
                    .getTen()
                    .getValue();
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inner value not specified");
        }
    }

    /*
     * This solution uses Java Bean Validation and is just as consice as doing no
     * null checking at all. The only difference being the @Valid annotation on the
     * request parameter. The details of how to validate the objects sit on the fields
     * of the classes being validated.
     * 
     * This solution is not always possible, especially not in instances where the classes being
     * validated are generated from a WSDL file or an OpenApi/Swagger spec.
     */
    @PostMapping(path = "5-null-checks-with-bean-validation")
    @ResponseStatus(HttpStatus.CREATED)
    public String getInnerValueNullChecksWithBeanValidation(@RequestBody @Valid Request request) {
        log.info(request.toString());
        return request
                .getOne()
                .getTwo()
                .getThree()
                .getFour()
                .getFive()
                .getSix()
                .getSeven()
                .getEight()
                .getNine()
                .getTen()
                .getValue();
    }
}