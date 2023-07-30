import { Construct } from 'constructs';
import { App, Chart, ChartProps } from 'cdk8s';
import { KubeConfigMap, KubeDeployment, Quantity } from './imports/k8s';

export class MyChart extends Chart {
  constructor(scope: Construct, id: string, props: ChartProps = {}) {
    super(scope, id, props);

    const appName = 'diceware'
    const label = { app: appName };

    // define resources here
    new KubeDeployment(this, 'diceware-deployment', {
      metadata: {
        name: 'diceware-deployment',
        labels: label
      },
      spec: {
        replicas: 2,
        selector: {
          matchLabels: label
        },
        template: {
          metadata: { labels: label },
          spec: {
            containers: [
              {
                name: appName,
                image: 'yannisf/diceware',
                ports: [{ containerPort: 8080 }],
                resources: {
                  limits: {
                    cpu: Quantity.fromString('250m'),
                    memory: Quantity.fromString('512Mi'),
                  },
                  requests: {
                    cpu: Quantity.fromString('250m'),
                    memory: Quantity.fromString('512Mi'),
                  }
                },
                imagePullPolicy: 'IfNotPresent',
                env: [
                  {
                    name: "CONCAT_MODE",
                    valueFrom: {
                      configMapKeyRef: {
                        name: 'diceware-configmap',
                        key: 'concat_mode'
                      }
                    }
                  }
                ],
                volumeMounts: [
                  { name: 'config', mountPath: '/config', readOnly: true }
                ]
              }
            ],
            volumes: [
              { name: 'config', configMap: { name: 'diceware-configmap', items: [{ key: 'log4j.xml', path: 'log4j.xml' }] } }
            ]
          }
        }
      }
    });

    new KubeConfigMap(this, 'diceware-configmap', {
      metadata: {
        name: 'diceware-deployment',
        labels: label
      },
      data: {
        concat_mode: 'kebab',
        log4j_xml: `multiline
        string
        file`
      }
    });

  }
}

const app = new App();
new MyChart(app, 'diceware');
app.synth();
