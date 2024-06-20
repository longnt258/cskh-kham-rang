using manager_application.Interfaces;
using manager_application.Models;
using manager_application.Services;
using manager_application.UserControlls;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Threading.Tasks;
using System.Windows.Forms;

// Màn hiển thị thông báo
namespace manager_application.UserControls
{
    public partial class NotificationPanel : UserControl
    {
        private List<Notification> notifications = new List<Notification>();
        private readonly NotificationService notificationService;
        private System.Windows.Forms.Timer notificationTimer;
        private NotificationInterface notificationInterface;

        public NotificationPanel(NotificationInterface notificationInterface)
        {
            this.notificationInterface = notificationInterface;
            notificationService = new NotificationService();
            InitializeComponent();
            InitializeTimer();
        }


        // Khởi tạo the bộ đếm giờ
        private void InitializeTimer()
        {
            notificationTimer = new Timer
            {
                Interval = 5000 // 60000 ms = 1 phút
            };
            // Cài đặt sau 1p sẽ gọi hàm OnTimerTick
            notificationTimer.Tick += new EventHandler(OnTimerTick);
            // Chạy bộ đếm giờ
            notificationTimer.Start();
        }

        protected override async void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            await InitView();
        }

        // Lấy dữ liệu 
        private async Task InitView()
        {
            notifications = await notificationService.GetAllNotification();
            if (notifications.Count > 0)
            {
                UpdateNotificationItems();
            }
            else
            {
                Console.WriteLine("No notifications found.");
            }
        }

        // Sau khi bộ đếm giờ đếm đến 0 sẽ chạy hàm này
        private async void OnTimerTick(object sender, EventArgs e)
        {
            await CheckForNewNotifications();
        }

        // Kiểm tra xem có thông báo mới không
        private async Task CheckForNewNotifications()
        {
            var newNotifications = await notificationService.GetAllNotification();
            if (newNotifications.Count != notifications.Count)
            {
                notifications = newNotifications;
                // Update UI cho nút thông báo ở Dashboard
                notificationInterface.updateNewNotify(true);
                UpdateNotificationItems();
            }
        }

        // Tạo ra 1 NotificationItem rồi thêm vào UI
        private void UpdateNotificationItems()
        {
            flowLayoutPanel1.Controls.Clear();
            foreach (var notification in notifications)
            {
                NotificationItem item = new NotificationItem
                {
                    Content = notification.Content,
                    NotifTime = (DateTime.Now - notification.CreatedDate).ToString(@"mm") + "  Phút trước",
                    Id = notification.Id,
                    BackColor = notification.Status == true ? Color.AliceBlue : Color.Red,
                };
                flowLayoutPanel1.Controls.Add(item);
            }
        }
    }
}
