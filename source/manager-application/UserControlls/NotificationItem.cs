using manager_application.Models;
using manager_application.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

// Item cho list thông báo
namespace manager_application.UserControlls
{
    public partial class NotificationItem : UserControl
    {
        public NotificationItem()
        {
            InitializeComponent();
        }

        private int _id;
        private string content;
        private string notifTime;

        [Category("Custom Props")]
        public string Content
        {
            get { return content; }
            set { content = value; lbContent.Text = value; }
        }

        [Category("Custom Props")]
        public string NotifTime
        {
            get { return notifTime; }
            set { notifTime = value; lbNotifTime.Text = value; }
        }

        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }

        // Sự kiện nhấn chuột vào Item (Khi nhấn sẽ gọi hàm update thông báo nếu thành công thì đổi màu background
        private async void NotificationItem_MouseClick(object sender, MouseEventArgs e)
        {
            NotificationService service = new NotificationService();
            Notification notification = await service.UpdateNotificatioNStatus(Id);
            if (notification != null)
            {
                BackColor = Color.AliceBlue;
            }
            
        }
    }
}
