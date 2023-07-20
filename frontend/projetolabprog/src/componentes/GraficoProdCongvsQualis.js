import {Bar} from 'react-chartjs-2'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);


const config = {
  responsive              : true,
  scales: {
    xAxes: [{
      stacked: true,
    }],
    yAxes: [{
      stacked: true
    }]
  }
}

export default function graficoProdPervsQualis({dados}) {

  const grafico = {
    labels  : dados.ano,
    datasets: [
      {
        label               : 'A1',
        data                : dados.a1,
        backgroundColor     : '#4dc9f6'
      },
      {
        label               : 'A2',
        data                : dados.a2,
        backgroundColor     : '#f67019'
      },
      {
        label               : 'A3',
        data                : dados.a3,
        backgroundColor     : '#537bc4'
      },
      {
        label               : 'A4',
        data                : dados.a4,
        backgroundColor     : '#acc236'
      },
    ]
  }

  return (
      <div class="card card-gray">
        <div class="card-header">
          <h3 class="card-title">Produção em Congresso vs Qualis</h3>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse">
              <i class="fas fa-minus"></i>
            </button>
            <button type="button" class="btn btn-tool" data-card-widget="remove">
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
          <div class="chart">
            <Bar options={config} data = {grafico} />
          </div>
        </div>
        
      </div>
  );
}