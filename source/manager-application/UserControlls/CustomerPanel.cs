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
    public partial class CustomerPanel : UserControl
    {
        private readonly CustomerService customerApi;

        public CustomerPanel()
        {
            InitializeComponent();
            customerApi = new CustomerService();
            InitView();
        }

        async void InitView()
        {

            dataGridView1.Rows.Clear();
            Response<List<Customer>> res = await customerApi.GetAllCustomer();
            if (res.Status == 1)
            {
                for (int i = 0; i < res.data.Count; i++)
                {
                    Customer customer = res.data[i];
                    dataGridView1.Rows.Add(new object[]
                    {
                        i.ToString(),
                        customer.FullName,
                        customer.Email,
                        customer.PhoneNumber
                    });
                }
            }
        }

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
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

                // Add a MenuItem to the ContextMenu
                MenuItem menuItemShowSchedules = new MenuItem("Show Schedules");
                menuItemShowSchedules.Click += MenuItemShowSchedules_Click;
                m.MenuItems.Add(menuItemShowSchedules);

                // Show the ContextMenu at the location of the mouse click
                m.Show(dataGridView1, dataGridView1.PointToClient(Cursor.Position));
            }
        }


        private void MenuItemShowSchedules_Click(object sender, EventArgs e)
        {
            // Get the selected row
            DataGridViewRow selectedRow = dataGridView1.SelectedRows[0];

            // Retrieve relevant data from the selected row
            // Assuming the relevant data is stored in specific columns of the DataGridView
            string scheduleData = selectedRow.Cells["colCusEmail"].Value.ToString(); // Replace "ColumnName" with the actual column name

            //// Open another form passing the relevant data
            //// Assuming you have another form named ScheduleForm to display the schedule data
            MessageBox.Show(scheduleData);
            //ScheduleForm scheduleForm = new ScheduleForm(scheduleData);
            //scheduleForm.Show();
        }

    }
}
