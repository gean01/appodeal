export interface GeanAppodealPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
