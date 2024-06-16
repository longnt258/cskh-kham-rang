using manager_application.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application.UserControlls
{
    public partial class NotificationItem : UserControl
    {
        public NotificationItem()
        {
            InitializeComponent();
        }

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

    }
}
