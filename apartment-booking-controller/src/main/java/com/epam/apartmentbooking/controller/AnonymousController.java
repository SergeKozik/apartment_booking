package com.epam.apartmentbooking.controller;

import com.epam.apartmentbooking.bean.*;
import com.epam.apartmentbooking.service.ApartmentService;
import com.epam.apartmentbooking.service.SharedResourceHolder;
import com.epam.apartmentbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * Created by Serge_Kozik on 5/3/2017.
 */
@Controller
public class AnonymousController {

    @Autowired
    private UserService userService;

    @Autowired
    private SharedResourceHolder sharedResourceHolder;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private MessageSource messageSource;

    private void addBlankApartmentCriteriaBean(ModelAndView mav, Locale locale) {
        mav.addObject("booking_params",new CriteriaApartmentBean(locale));
    }

    private String loginUserByProfileBean(UserProfileBean profileBean, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userProfile",profileBean);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(profileBean.getEmail(),password));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Object linkObj = session.getAttribute("returnPage");
        if (linkObj==null) {
            return "main.html";
        } else {
            session.setAttribute("returnPage",null);
            return (String)linkObj;
        }
    }

    @ModelAttribute("room_types")
    public Map<String,String> addRoomTypes(Locale locale) {
        return sharedResourceHolder.returnLocalizedRoomTypes(locale);
    }

    @RequestMapping("/main.html")
    public ModelAndView doMainPage(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("main.page");
        addBlankApartmentCriteriaBean(modelAndView,locale);
        List<ApartmentViewBean> apartments = apartmentService.showAll();
        modelAndView.addObject("apartments",apartments);
        return modelAndView;
    }

    @RequestMapping("/show-homes.html")
    public ModelAndView doFilterApartments(@ModelAttribute("booking_params") @Valid CriteriaApartmentBean criteriaApartment, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("main.page","booking_params",criteriaApartment);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        List<ApartmentViewBean> apartments = apartmentService.filterAvailable(criteriaApartment);
        modelAndView.addObject("apartments",apartments);
        return modelAndView;
    }

    @RequestMapping("/register.html")
    public ModelAndView doShowRegisterForm() {
        return new ModelAndView("register.page","register_user",new UserRegisterBean());
    }

    @RequestMapping(value = "/register-user.html", method = RequestMethod.POST)
    public ModelAndView doRegisterUser(@ModelAttribute("register_user") @Valid UserRegisterBean registerBean, BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register.page","register_user",registerBean);
        }
        UserProfileBean profileBean = userService.registerUser(registerBean);
        if (profileBean==null) {
            ModelAndView mav = new ModelAndView("register.page","register_user",registerBean);
            mav.addObject("register_error_message",messageSource.getMessage("message.error.register-fault",null,locale));
            return mav;
        }
        String returnLink=loginUserByProfileBean(profileBean,registerBean.getPassword(),request);
        return new ModelAndView("redirect:/"+returnLink);
    }

    @RequestMapping("/login.html")
    public ModelAndView doLoginForm() {
        return new ModelAndView("login.page","login_user",new UserLoginBean());
    }

    @RequestMapping(value = "/login-user.html", method = RequestMethod.POST)
    public ModelAndView doLoginUser(@ModelAttribute("login_user") @Valid UserLoginBean loginBean, BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("login.page","login_user",loginBean);
        }
        UserProfileBean profileBean = userService.loginUser(loginBean);
        if (profileBean==null) {
            ModelAndView mav = new ModelAndView("login.page","login_user",loginBean);
            mav.addObject("login_error_message",messageSource.getMessage("message.error.login-fault",null,locale));
            return mav;
        }
        String returnLink=loginUserByProfileBean(profileBean,loginBean.getPassword(),request);
        return new ModelAndView("redirect:/"+returnLink);
    }
}