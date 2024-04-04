using manager_application.models;
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

namespace manager_application
{
    public partial class LoginFrm : Form
    {
        private readonly AuthServices authServices;
        public LoginFrm()
        {
            InitializeComponent();
            authServices = new AuthServices();
        }



        private async void BtnLogin_Click(object sender, EventArgs e)
        {
            string userName = tbUserName.Text;
            string password = tbPassword.Text;
            if(userName == "")
            {
                MessageBox.Show("The User name isn't allowed to empty");
            }
            else if (password == "")
            {
                MessageBox.Show("The Password isn't allowed to empty");
            }
            else
            {
                Response<Admin> response = await authServices.Login(userName, password);
                if (response.Status != 1)
                {
                    MessageBox.Show(response.Message);
                }
                else
                {
                    Program.admin = response.data;
                    DashboardFrm dashboardFrm = new DashboardFrm(this);
                    dashboardFrm.Show();
                }
            }
        }

        private void LbSignUp_Click(object sender, EventArgs e)
        {
            new SignUpFrm(this).Show();
            Hide();
        }

        private void BtnCancel_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
