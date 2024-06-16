using manager_application.Models;
using manager_application.Services;
using manager_application.UserControlls;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application.UserControls
{
    public partial class NotificationPanel : UserControl
    {
        private List<Notification> notifications;
        private readonly NotificationService notificationService;
        private System.Windows.Forms.Timer notificationTimer;

        public NotificationPanel()
        {
            notificationService = new NotificationService();
            InitializeComponent();
            InitializeTimer();
        }

        // Tạo bộ đếm giờ 
        private void InitializeTimer()
        {
            notificationTimer = new Timer
            {
                Interval = 60000 // 60000 ms = 1 phút
            };
            notificationTimer.Tick += new EventHandler(OnTimerTick);
            notificationTimer.Start();
        }

        protected override async void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            await InitView();
        }

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

        // Khai báo hành động khi timer đếm về 0
        private async void OnTimerTick(object sender, EventArgs e)
        {
            await CheckForNewNotifications();
        }

        // Kiểm tra thông báo mới
        private async Task CheckForNewNotifications()
        {
            var newNotifications = await notificationService.GetAllNotification();
            // Check nếu có thông báo mới sẽ update lại UI
            if (newNotifications.Count != notifications.Count)
            {
                notifications = newNotifications;
                UpdateNotificationItems();
            }
        }

        private void UpdateNotificationItems()
        {
            flowLayoutPanel1.Controls.Clear();
            foreach (var notification in notifications)
            {
                NotificationItem item = new NotificationItem
                {
                    Content = notification.Content,
                    NotifTime = (DateTime.Now - notification.createdDate).ToString(@"mm") + "  Phút trước"
                };
                flowLayoutPanel1.Controls.Add(item);
            }
        }
    }
}
