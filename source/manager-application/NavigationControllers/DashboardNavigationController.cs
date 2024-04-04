using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application.NavigationControllers
{
    public class DashboardNavigationController
    {
        private readonly List<UserControl> userControls = new List<UserControl>();
        private readonly Panel panel;

        public DashboardNavigationController(List<UserControl> userControls, Panel panel)
        {
            this.userControls = userControls;
            this.panel = panel;
            AddUserControlls();
        }

        private void AddUserControlls()
        {
            for(int i = 0; i < userControls.Count; i++)
            {
                userControls[i].Dock = DockStyle.Fill;
                panel.Controls.Add(userControls[i]);
            }
        }

        public void Display(int index)
        {
            if(index < userControls.Count())
            {
                userControls[index].BringToFront();
            }
        }
    }
}
