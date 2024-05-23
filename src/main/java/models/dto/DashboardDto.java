package models.dto;


public class DashboardDto {
    private ObservableList<PieChart.Data> genderChartData;
    private int totalMaleUsers;
    private int totalFemaleUsers;
    private int totalUsers;
    private ObservableList<PieChart.Data> dormChartData;
    private int maleDormCount;
    private int femaleDormCount;
    private int totalDormCount;

    public DashboardDTO(ObservableList<PieChart.Data> genderChartData, int totalMaleUsers, int totalFemaleUsers, int totalUsers,
                        ObservableList<PieChart.Data> dormChartData, int maleDormCount, int femaleDormCount, int totalDormCount) {
        this.genderChartData = genderChartData;
        this.totalMaleUsers = totalMaleUsers;
        this.totalFemaleUsers = totalFemaleUsers;
        this.totalUsers = totalUsers;
        this.dormChartData = dormChartData;
        this.maleDormCount = maleDormCount;
        this.femaleDormCount = femaleDormCount;
        this.totalDormCount = totalDormCount;
    }

    public ObservableList<PieChart.Data> getGenderChartData() {
        return genderChartData;
    }

    public int getTotalMaleUsers() {
        return totalMaleUsers;
    }

    public int getTotalFemaleUsers() {
        return totalFemaleUsers;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public ObservableList<PieChart.Data> getDormChartData() {
        return dormChartData;
    }

    public int getMaleDormCount() {
        return maleDormCount;
    }

    public int getFemaleDormCount() {
        return femaleDormCount;
    }

    public int getTotalDormCount() {
        return totalDormCount;
    }
}
