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
        private List<Schedule> schedules;

        public SchedulePanel()
        {
            InitializeComponent();
            service = new ScheduleService();
            schedules = new List<Schedule>();
            InitView();
        }

        public async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<Schedule>> response = await service.GetAllSchedules();
            if (response.Status == 1)
            {
                schedules = response.data;

                for (int i = 0; i < response.data.Count; i++)
                {
                    Schedule schedule = response.data[i];
                    string status;
                    if (schedule.Status == 1)
                    {
                        status = "Đang xử lý";
                    }
                    else if (schedule.Status == 2)
                    {
                        status = "Đã đặt thành công";
                    }
                    else
                    {
                        status = "Đã hủy";
                    }
                    dataGridView1.Rows.Add(new object[] {
                        i.ToString(),
                        schedule.PhoneNumber,
                        schedule.Title,
                        schedule.Description,
                        schedule.BookDateTime.ToString(),
                        schedule.DentistName,
                        schedule.UserFullName,
                        status
                    });
                }
            }
        }

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }

        private void BtnAdd_Click(object sender, EventArgs e)
        {
            AddScheduleFrm addScheduleFrm = new AddScheduleFrm();
            addScheduleFrm.ShowDialog();
        }

        private void DataGridView1_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            if (e.Button == MouseButtons.Right && e.RowIndex >= 0)
            {
                // Select the row that was clicked on
                dataGridView1.ClearSelection();
                dataGridView1.Rows[e.RowIndex].Selected = true;
                dataGridView1.CurrentCell = dataGridView1.Rows[e.RowIndex].Cells[e.ColumnIndex];

                // Create a ContextMenu
                ContextMenu m = new ContextMenu();

                MenuItem menuItemScheduleAuthorize = new MenuItem("Xác nhận lịch");
                menuItemScheduleAuthorize.Click += MenuItemScheduleAuthorize_Click;
                m.MenuItems.Add(menuItemScheduleAuthorize);
                MenuItem menuItemScheduleCancel = new MenuItem("Hủy lịch");
                menuItemScheduleCancel.Click += MenuItemOpenEditorScheduleCancel_Click;
                m.MenuItems.Add(menuItemScheduleCancel);
                // Show the ContextMenu at the location of the mouse click
                m.Show(dataGridView1, dataGridView1.PointToClient(Cursor.Position));
            }
        }

        private async void MenuItemOpenEditorScheduleCancel_Click(object sender, EventArgs e)
        {
            // Get the selected row
            int selectedRow = dataGridView1.CurrentCell.RowIndex;
            Schedule selectedSchedule = schedules[selectedRow];
            selectedSchedule.Status = 0;
            Response<Schedule> response = await service.update(selectedSchedule);
            if (response.Status == 1)
            {
                MessageBox.Show(response.Message);
                InitView();
            }
            else
            {
                MessageBox.Show(response.Message);
            }
        }
        private async void MenuItemScheduleAuthorize_Click(object sender, EventArgs e)
        {
            // Get the selected row
            int selectedRow = dataGridView1.CurrentCell.RowIndex;
            Schedule selectedSchedule = schedules[selectedRow];
            selectedSchedule.Status = 2;
            Response<Schedule> response = await service.update(selectedSchedule);
            if (response.Status == 1)
            {
                MessageBox.Show(response.Message);
                InitView();
            }
            else
            {
                MessageBox.Show(response.Message);
            }
        }

        private void btnFindByPhoneNumber_Click(object sender, EventArgs e)
        {
            string phoneNumber = textBox1.Text;
            if (phoneNumber.Length == 0)
            {
                InitView();
            }
            List<Schedule> findByPhoneSchedules = new List<Schedule>
            {
                schedules.Find(s => s.PhoneNumber.Contains(phoneNumber))
            };
            if(findByPhoneSchedules.Count > 0)
            {
                for (int i = 0; i < findByPhoneSchedules.Count; i++)
                {
                    Schedule schedule = findByPhoneSchedules[i];
                    string status;
                    if (schedule.Status == 1)
                    {
                        status = "Đang xử lý";
                    }
                    else if (schedule.Status == 2)
                    {
                        status = "Đã đặt thành công";
                    }
                    else
                    {
                        status = "Đã hủy";
                    }
                    dataGridView1.Rows.Add(new object[] {
                        i.ToString(),
                        schedule.PhoneNumber,
                        schedule.Title,
                        schedule.Description,
                        schedule.BookDateTime.ToString(),
                        schedule.DentistName,
                        schedule.UserFullName,
                        status
                    });
                }
            }
            
        }
    }
}

