package gulimall.order.web;

import gulimall.order.service.OrderService;
import gulimall.order.vo.MemberAddressVo;
import gulimall.order.vo.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;


/**
 * @author 孙启新
 * <br>FileName: OrderWebController
 * <br>Date: 2020/08/08 15:30:23
 */
@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;

    /**
     * 跳转结算页，并展示当前需要展示的订单信息
     *
     * @param model model
     * @return orderConfirmVo
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @GetMapping("/toTrade")
    public String toTrade(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo orderConfirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmVo", orderConfirmVo);
        return "confirm";
    }

    /**
     * 更改当前的默认地址为新指定的
     *
     * @param memberId      用户id
     * @param addressId     要更改成默认地址的列id
     * @param defaultStatus 要更改成的信息
     * @return 结算页
     */
    @GetMapping("/updateAddress")
    public String updateAddress(@RequestParam("memberId") Long memberId, @RequestParam("defaultStatus") Integer defaultStatus, @RequestParam("addressId") Long addressId) {
        orderService.updateAddress(memberId, defaultStatus, addressId);
        return "redirect:http://order.gulimall.com/toTrade";
    }
}
