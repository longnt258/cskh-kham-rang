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
    public partial class AddOrUpdateDentist : Form
    {

        private readonly Dentist dentist;
        private readonly DentistService dentistService;
        private readonly bool isEdit;

        public AddOrUpdateDentist(Dentist dentist)
        {
            isEdit = true;
            dentistService = new DentistService(); 
            this.dentist = dentist;
            InitializeComponent();
            lbContentDialog.Text = "CHỈNH SỬA NHA SĨ";

            tbName.Text = dentist.FullName;
            if (dentist.Status)
            {
                rdbActive.Checked = true;
            }
            else
            {
                rdbInActive.Checked = true;
            }

            dtpStartDate.Value = dentist.StartDateTime.Date;
            dtpEndDate.Value = dentist.EndDateTime.Date;

        }

        public AddOrUpdateDentist()
        {
            isEdit = false;
            InitializeComponent();
            dentistService = new DentistService();
            lbContentDialog.Text = "THÊM NHA SĨ";
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            if (isEdit)
            {
                PerformEdit();
            }
            else
            {
                PerformInsert();
            }

        }

        private async void PerformEdit() 
        {
            dentist.FullName = tbName.Text;
            if (rdbActive.Checked)
            {
                dentist.Status = true;
            }
            else if (rdbInActive.Checked)
            {
                dentist.Status = false;
            }
            dentist.StartDateTimeString = dtpStartDate.Value.ToString();
            dentist.EndDateTimeString = dtpEndDate.Value.ToString();
            Response<Dentist> res = await dentistService.Update(dentist.DentistId,dentist);
            if (res.Status == 1)
            {
                MessageBox.Show("Sửa Thành Công");
                Close();
            }
            else
            {
                MessageBox.Show(res.Message);
                Close();
            }

        }

        private async void PerformInsert()
        {
            Dentist dentist = new Dentist
            {
                FullName = tbName.Text
            };
            if (rdbActive.Checked)
            {
                dentist.Status = true;
            }
            else if (rdbInActive.Checked)
            {
                dentist.Status = false;
            }
            dentist.StartDateTimeString = dtpStartDate.Value.ToString();
            dentist.EndDateTimeString = dtpEndDate.Value.ToString();
            Response<Dentist> res = await dentistService.Insert(dentist);
            if (res.Status == 1)
            {
                MessageBox.Show("Thêm Thành Công");
                Close();
            }
            else
            {
                MessageBox.Show(res.Message);
                Close();
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Dispose();
        }
    }
}
