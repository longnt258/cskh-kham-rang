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
    public partial class HistoryCallPanel : UserControl
    {
        private readonly HistoryCallService historyCallingService;

        public HistoryCallPanel()
        {
            historyCallingService = new HistoryCallService();
            InitializeComponent();
            InitView();
        }

        private async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<CallingHistory>> res = await historyCallingService.GetAll();
            if(res.Status == 1)
            {
                for (int i = 0; i < res.data.Count; i++)
                {
                    CallingHistory ch = res.data[i];
                    dataGridView1.Rows.Add(new object[]
                    {
                        i.ToString(),
                        ch.Status? "Complete" : "Missing",
                        ch.Description,
                        ch.StartDate,
                        ch.EndDate ?? "",
                        ch.UserFullName,
                        ch.PhoneNumber
                    });
                }
            }
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }
    }
}
