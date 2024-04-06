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

namespace manager_application.UserControlls
{
    public partial class SchedulePanel : UserControl
    {
        private readonly ScheduleService service;

        public SchedulePanel()
        {
            InitializeComponent();
            service = new ScheduleService();
            InitView();
        }

        private async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<Schedule>> response = await service.GetAllSchedules();
            if (response.Status == 1) 
            {
                for(int i = 0; i < response.data.Count; i++) {
                    Schedule schedule = response.data[i];
                    dataGridView1.Rows.Add(new object[] {
                        i.ToString(),
                        schedule.Code,
                        schedule.Title,
                        schedule.Description,
                        schedule.BookDateTime.ToString(),
                        schedule.DentistName,
                        schedule.UserFullName
                    });
                }
            }
        }

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }
    }
}
