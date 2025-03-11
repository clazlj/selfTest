package ffmpeg;

import org.bytedeco.javacv.*;

/**
 * @author cl
 */
public class Video2AudioDemo {
    public static void main(String[] args) {
        // 视频文件路径
        String videoFilePath = "C:\\Users\\admin3\\Downloads\\2025_03_10 19_28_17.ts";
        // 输出音频文件路径
        String outputAudioPath = "output_audio.wav";
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath);
             FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputAudioPath, 1)) {

            grabber.start();

            // 获取音频参数
            int audioChannels = grabber.getAudioChannels();
            int audioSampleRate = grabber.getSampleRate();

            // 配置录音器
            recorder.setAudioChannels(audioChannels);
            recorder.setSampleRate(audioSampleRate);
            recorder.setAudioCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_PCM_S16LE); // WAV 格式
            recorder.start();

            // 抓取并保存音频帧
            Frame frame;
            while ((frame = grabber.grabSamples()) != null) {
                recorder.record(frame);
            }

            // 停止抓取器和录音器
            recorder.stop();
            grabber.stop();
            System.out.println("音频已保存为: " + outputAudioPath);
        } catch (FrameGrabber.Exception e) {
            throw new RuntimeException(e);
        } catch (FrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }

    }
}
