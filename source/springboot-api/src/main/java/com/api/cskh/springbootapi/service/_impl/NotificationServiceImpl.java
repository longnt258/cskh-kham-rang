package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.Notification;
import com.api.cskh.springbootapi.domain.Schedule;
import com.api.cskh.springbootapi.repository.NotificationRepository;
import com.api.cskh.springbootapi.repository.ScheduleRepository;
import com.api.cskh.springbootapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Notification> findAll() {
        List<Notification> notifications = notificationRepository.findAll();
        notifications.sort(Comparator.comparing(Notification::getCreatedDate).reversed());
        return notifications;
    }

    @Override
    public void runAutoNotification() {
        // schedule status = 2
        // ngày đặt lớn hơn ngày hiện tại (ngày đặt - ngày hiện tại ==> 6 tiếng ==> báo)
        // lấy danh sách schedules ==> check status = 2 và ngày

        // Lấy danh sách schedules (Đặt lịch thành công và chưa thông báo status = 2 và notif = false)
        List<Schedule> schedules = scheduleRepository.findScheduleByStatusAndNotif(2, false);
        // Check ngày đặt thành công lớn hơn ngày hiện tại (bookingDate)
        for(Schedule s : schedules) {
            long remaindedHours = DateUtil.getHoursRemained(s.getBookingDatetime(), new Date()); // lấy ngày lớn hơn
            if(remaindedHours > 0 && remaindedHours <= 6) {
                LogUtil.logger.info("Tạo thông báo cho Schedule có mã: " + s.getCode());

                // Tạo thông báo từ các Schedules còn 6 tiếng và cập nhật trạng thái đã thong báo cho Schedule (notif = true)
                Notification n = new Notification();
                n.setContent("Lich hen " + s.getTitle() + "\nTen khach hang" + s.getUser().getFullName() + "\nSo dien thoai" + s.getUser().getPhoneNumber() + " sap den vao luc " + DateUtil.convertDate2String(s.getBookingDatetime()));
                notificationRepository.save(n);

                s.setNotif(true);
                scheduleRepository.save(s);
            }
        }
    }

    @Override
    public Notification updateStatus(Integer notificationId) {
        Notification n = notificationRepository.findById(notificationId).orElseThrow();
        n.setStatus(true);
        return notificationRepository.save(n);
    }
}
