export interface GeanAppodealPlugin {
  showInterstitial(options: { value: string }): Promise<{ value: string }>;
  setTesting(options: { value: boolean }): Promise<{ value: boolean }>;
  setKey(options: { key: string }): Promise<{ key: string }>;
}
