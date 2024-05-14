using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application
{
    public partial class SignUpFrm: Form
    {
        private readonly LoginFrm loginFrm;

        public SignUpFrm(LoginFrm frm)
        {
            InitializeComponent();
            loginFrm = frm;
        }

        private void LbLogIn_Click(object sender, EventArgs e)
        {
            loginFrm.Show();
            Hide();
        }

        private void BtnSignUp_Click(object sender, EventArgs e)
        {
            //HttpClient client = new HttpClient();


        }

        private void BtnCancel_Click(object sender, EventArgs e)
        {
            loginFrm.Show();
            Hide();
        }

        private void SignUpFrm_FormClosing(object sender, FormClosingEventArgs e)
        {
            // Prompt the user with a confirmation dialog
            if (MessageBox.Show("Are you sure you want to exit?", "Confirm Exit", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
            {
                // If the user clicks No, cancel the closing event to prevent the form from closing
                e.Cancel = true;
            }
            // If the user clicks Yes, the closing event will proceed and the form will close
            loginFrm.Close();
        }
    }
}
