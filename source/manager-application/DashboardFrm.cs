using manager_application.NavigationControllers;
using manager_application.UserControlls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application
{
    public partial class DashboardFrm : Form
    {

        private readonly Form previousFrom;
        private DashboardNavigationController controller;

        public DashboardFrm(Form previouseFrm)
        {
            InitializeComponent();
            previousFrom = previouseFrm;
            previouseFrm.Hide();
            InitializeNavigationController();
        }

        void InitializeNavigationController()
        {
            List<UserControl> list = new List<UserControl>()
            {
                new DentistPanel(),
                new CustomerPanel(),
                new HistoryCallPanel(),
                new SchedulePanel(),
                new SettingPanel()
            };
            controller = new DashboardNavigationController(list, panelContent);
            controller.Display(0);
        }

        private void BtnDentist_Click(object sender, EventArgs e)
        {
            controller.Display(0);
        }

        private void BtnCustomer_Click(object sender, EventArgs e)
        {
            controller.Display(1);
        }

        private void BtnSchedule_Click(object sender, EventArgs e)
        {
            controller.Display(2);
        }

        private void BtnHistoryCall_Click(object sender, EventArgs e)
        {
            controller.Display(3);
        }

        private void BtnSetting_Click(object sender, EventArgs e)
        {
            controller.Display(4);
        }

        private void DashboardFrm_FormClosing(object sender, FormClosingEventArgs e)
        {
            // Prompt the user with a confirmation dialog
            if (MessageBox.Show("Are you sure you want to exit?", "Confirm Exit", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
            {
                // If the user clicks No, cancel the closing event to prevent the form from closing
                e.Cancel = true;
            }
            // If the user clicks Yes, the closing event will proceed and the form will close
            previousFrom.Close();
        }
    }
}
