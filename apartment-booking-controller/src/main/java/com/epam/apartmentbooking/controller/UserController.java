package com.epam.apartmentbooking.controller;

import com.epam.apartmentbooking.bean.*;
import com.epam.apartmentbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Roldo on 13.05.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomTimeFieldHandler timeFieldHandler;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping("booking-list.html")
    public String doBookingList(HttpServletRequest request) {
        Object currentUser = request.getSession().getAttribute("userProfile");
        if (currentUser!=null) {
            UserProfileBean userProfileBean = (UserProfileBean)currentUser;
            List<BookingViewBean> bookings = bookingService.showByUser(userProfileBean);
            request.setAttribute("bookings",bookings);
        }
        return "user-bookings.page";
    }

    @RequestMapping("book/{id}")
    public ModelAndView doShowBookingPage(@PathVariable("id") int id, HttpServletRequest request, Locale locale)  {
        ApartmentViewBean apartmentViewBean = apartmentService.showOne(id);
        if (apartmentViewBean==null) {
            return new ModelAndView("redirect:/main.html");
        }
        Object userObj = request.getSession().getAttribute("userProfile");
        if (userObj==null) {
            return new ModelAndView("redirect:/main.html");
        }
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        int userId=userProfileBean.getId();
        String nowDateStr = timeFieldHandler.showCurrentDateString();
        ModelAndView mav = new ModelAndView("booking.page","apartment_booking",new BookingBookBean(id,userId,nowDateStr,nowDateStr,0));
        request.setAttribute("apartment",apartmentViewBean);
        RoomTypeEnum roomTypeEnum = apartmentViewBean.getSpace().getRoomType();
        request.setAttribute("roomType_local",messageSource.getMessage(roomTypeEnum.getPropertiesCode(),null,locale));
        return mav;
    }

    @RequestMapping("book-apartment.html")
    public ModelAndView doBookApartment(@ModelAttribute("apartment_booking") @Valid BookingBookBean bookBean, BindingResult bindingResult, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("booking.page","apartment_booking",bookBean);
        }
        if (bookingService.bookApartment(bookBean)) {
            return new ModelAndView("/user/booking-list.html");
        } else {
            ModelAndView mav = new ModelAndView("booking.page","apartment_booking",bookBean);
            mav.addObject("booking_error_message",messageSource.getMessage("message.error.booking-fault",null,locale));
            return mav;
        }
    }

    @RequestMapping("profile.html")
    public ModelAndView doShowProfilePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("userProfile");
        if (userObj==null) {
            return new ModelAndView("redirect:/main.html");
        }
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        return new ModelAndView("user-profile.page","change_profile",new UserProfileChangeBean(userProfileBean));
    }

    @RequestMapping("change-profile.html")
    public ModelAndView doShowProfilePage(@ModelAttribute("change_profile") @Valid UserProfileChangeBean changeBean, BindingResult bindingResult, Locale locale, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user-profile.page","change_profile",changeBean);
        }
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("userProfile");
        if (userObj==null) {
            return new ModelAndView("redirect:/main.html");
        }
        ModelAndView mav = new ModelAndView("user-profile.page","change_profile",changeBean);
        UserProfileBean userProfileBean = (UserProfileBean)userObj;
        UserProfileBean newProfile = userService.editProfile(userProfileBean,changeBean);
        if (newProfile==null) {
            mav.addObject("profile_error_message",messageSource.getMessage("message.error.profile-edit",null,locale));
            return mav;
        }
        session.setAttribute("userProfile",newProfile);
        mav.addObject("profile_success_message",messageSource.getMessage("message.profile-edit",null,locale));
        return mav;
    }
}
