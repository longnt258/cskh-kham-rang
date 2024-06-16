using manager_application.Interfaces;
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


        // Initialize the timer
        private void InitializeTimer()
        {
            notificationTimer = new Timer
            {
                Interval = 60000 // 60000 ms = 1 minute
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

        private async void OnTimerTick(object sender, EventArgs e)
        {
            await CheckForNewNotifications();
        }

        private async Task CheckForNewNotifications()
        {
            var newNotifications = await notificationService.GetAllNotification();
            if (newNotifications.Count != notifications.Count)
            {
                notifications = newNotifications;
                notificationInterface.updateNewNotify(true);
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
